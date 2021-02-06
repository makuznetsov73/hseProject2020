package hse.project.controllers.usercontrollers;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.AvailabilitySet;
import com.microsoft.azure.management.network.Network;
import com.microsoft.azure.management.network.NetworkInterface;
import com.microsoft.azure.management.network.PublicIPAddress;
import com.microsoft.azure.management.resources.ResourceGroup;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import hse.project.controllers.AbstractRepositoryPrevController;
import hse.project.entities.Response;
import hse.project.entities.api.mappers.TariffMapper;
import hse.project.entities.api.mappers.VirtualMachineMapper;
import hse.project.entities.api.previews.TariffPreview;
import hse.project.entities.api.previews.VirtualMachinePreview;
import hse.project.entities.mongo.AbstractMongoTariff;
import hse.project.entities.mongo.MongoCustomer;
import hse.project.entities.mongo.MongoVirtualMachine;
import hse.project.entities.prototypes.Tariff;
import hse.project.entities.prototypes.VirtualMachine;
import hse.project.mongo.repository.TariffRepositoryInterface;
import hse.project.mongo.repository.VMCustomerRepositoryInterface;
import hse.project.mongo.repository.VirtualMachineRepository;
import hse.project.utils.AzureAuth;
import hse.project.utils.AzureBillingUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user/vmachine")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class UserVirtualMachineController extends AbstractRepositoryPrevController<MongoVirtualMachine, VirtualMachine,
	VirtualMachinePreview, VirtualMachineMapper, VirtualMachineRepository> {
	
	private Azure azure;
	private VMCustomerRepositoryInterface customerRepository;
	
	UserVirtualMachineController(VirtualMachineRepository repository, VMCustomerRepositoryInterface customerRepository) {
		setRepository(repository);
		setMapper(new VirtualMachineMapper());
		azure = AzureAuth.getAzure();
		this.customerRepository = customerRepository;
		AzureBillingUtils.setVirtualMachineRepository(repository);
		AzureBillingUtils.setCustomerRepository(customerRepository);
	}
	
	@RequestMapping(value = "/get/myVMs", method = RequestMethod.GET)
	public Response<List<VirtualMachinePreview>> getPrev(@RequestHeader("username") String username) {
		List<MongoVirtualMachine> myVMs = getRepository().findByUsername(username);
		
		List<VirtualMachinePreview> myVMsPreview = new ArrayList<>();
		for (MongoVirtualMachine vm: myVMs) {
			myVMsPreview.add(getMapper().mapToPreview(vm));
		}
		
		return new Response<>(myVMsPreview, true);
	}
	
	@RequestMapping(value = "/turnOnOff", method = RequestMethod.POST)
	public Response<VirtualMachinePreview> turnOnOff(@RequestHeader("username") String username, @RequestBody String id) {
		Optional<MongoVirtualMachine> optionalMongoVirtualMachine = getRepository().findById(id);
		if (optionalMongoVirtualMachine.isPresent()) {
			MongoVirtualMachine mongoVirtualMachine = optionalMongoVirtualMachine.get();
			if (mongoVirtualMachine.getUsername().equals(username)) {
				com.microsoft.azure.management.compute.VirtualMachine vm = azure.virtualMachines()
					.getByResourceGroup(mongoVirtualMachine.getGroupId(), mongoVirtualMachine.getId());
				
				if (!mongoVirtualMachine.isBlocked()) {
					if (mongoVirtualMachine.isOn()) {
						vm.deallocate();
						mongoVirtualMachine.setOn(false);
						mongoVirtualMachine.setEndTime(null);
						mongoVirtualMachine.setStartPause(DateTime.now());
						mongoVirtualMachine = getRepository().save(mongoVirtualMachine);
						return new Response<>(getMapper().mapToPreview(mongoVirtualMachine), true);
					} else {
						vm.start();
						mongoVirtualMachine.setOn(true);
						mongoVirtualMachine.setEndTime(DateTime.now().plusDays(7)
							.minus(new Period(mongoVirtualMachine.getStartDate(), mongoVirtualMachine.getStartPause())));
						mongoVirtualMachine.setStartPause(null);
						mongoVirtualMachine.setIpAddress(vm.getPrimaryPublicIPAddress().ipAddress());
						mongoVirtualMachine = getRepository().save(mongoVirtualMachine);
						return new Response<>(getMapper().mapToPreview(mongoVirtualMachine), true);
					}
				} else {
					Optional<MongoCustomer> optionalCustomer = customerRepository.findById(mongoVirtualMachine.getUsername());
					if (optionalCustomer.isPresent()) {
						MongoCustomer customer = optionalCustomer.get();
						if (customer.getBalance() >= mongoVirtualMachine.getPrice()) {
							vm.start();
							customer.setBalance(customer.getBalance() - mongoVirtualMachine.getPrice());
							
							mongoVirtualMachine.setStartDate(DateTime.now());
							mongoVirtualMachine.setEndTime(DateTime.now().plusDays(7));
							mongoVirtualMachine.setBlocked(false);
							mongoVirtualMachine.setIpAddress(vm	.getPrimaryPublicIPAddress().ipAddress());
							customerRepository.save(customer);
							getRepository().save(mongoVirtualMachine);
							return new Response<>(getMapper().mapToPreview(mongoVirtualMachine), true);
						}
					}
				}
			}
			return new Response<>();
 		}
		return new Response<>();
	}
	
	@RequestMapping(value = "/createVM", method = RequestMethod.POST)
	public Response<String> createVirtualMachine(@RequestHeader("username") String username,
													   @RequestBody MongoVirtualMachine entity) {
		if (username.equals(entity.getUsername())) {
			Optional<MongoVirtualMachine> optionalMongoVirtualMachine = getRepository().findById(entity.getId());
			if (optionalMongoVirtualMachine.isPresent()) {
				return new Response<String>("Cannot create machine with such name. Please, choose another name", false);
			}
			
			try {
				Optional<MongoCustomer> optionalMongoCustomer = customerRepository.findById(username);
				MongoCustomer customer;
				if (optionalMongoCustomer.isPresent()) {
					customer = optionalMongoCustomer.get();
				} else {
					return new Response<String>("User not found", false);
				}
				
				double price = 1000;
				
				price *= AzureBillingUtils.getSizePriceK().get(entity.getSize());
				price *= AzureBillingUtils.getSkuPriceK().get(entity.getSku());
				
				entity.setPrice(price);
				
				if (customer.getBalance() < price) {
					return new Response<String>("Balance is too low", false);
				}
				
				entity.setStartDate(DateTime.now());
				entity.setEndTime(DateTime.now().plusDays(7));
				
				boolean resourceGroupExist = azure.resourceGroups().contain(entity.getGroupId());
				String resourceGroupStr;
				if (resourceGroupExist) {
					resourceGroupStr = entity.getGroupId();
				} else {
					ResourceGroup resourceGroup = azure.resourceGroups()
						.define(entity.getGroupId())
						.withRegion(Region.US_EAST)
						.create();
					resourceGroupStr = entity.getGroupId();
				}
				
				PublicIPAddress publicIPAddress = azure.publicIPAddresses()
					.define("IP_" + entity.getName() + "_" + entity.getUsername())
					.withRegion(Region.US_EAST)
					.withExistingResourceGroup(resourceGroupStr)
					.withDynamicIP()
					.create();
				
				System.out.println("Creating virtual network...");
				Network network = azure.networks()
					.define("VN_" + entity.getName() + "_" + entity.getUsername())
					.withRegion(Region.US_EAST)
					.withExistingResourceGroup(resourceGroupStr)
					.withAddressSpace("10.0.0.0/16")
					.withSubnet("Subnet_" + entity.getName() + "_" + entity.getUsername(), "10.0.0.0/24")
					.create();
				
				NetworkInterface networkInterface = azure.networkInterfaces()
					.define("NIC_" + entity.getName() + "_" + entity.getUsername())
					.withRegion(Region.US_EAST)
					.withExistingResourceGroup(resourceGroupStr)
					.withExistingPrimaryNetwork(network)
					.withSubnet("Subnet_" + entity.getName() + "_" + entity.getUsername())
					.withPrimaryPrivateIPAddressDynamic()
					.withExistingPrimaryPublicIPAddress(publicIPAddress)
					.create();
				
				com.microsoft.azure.management.compute.VirtualMachine virtualMachine = azure.virtualMachines()
					.define(entity.getId())
					.withRegion(Region.US_EAST)
					.withExistingResourceGroup(resourceGroupStr)
					.withExistingPrimaryNetworkInterface(networkInterface)
					.withLatestWindowsImage("MicrosoftWindowsServer", "WindowsServer", entity.getSku())
					.withAdminUsername(entity.getLogin())
					.withAdminPassword(entity.getPassword())
					.withComputerName(entity.getName())
					.withSize(entity.getSize())
					.create();
				
				customer.setBalance(customer.getBalance() - price);
				customer.addMachineId(entity.getId());
				customerRepository.save(customer);
				
				entity.setBlocked(false);
				entity.setOn(true);
				entity.setIpAddress(virtualMachine.getPrimaryPublicIPAddress().ipAddress());
				
				entity = getRepository().insert(entity);
				return new Response<String>("ok", true);
			} catch (Exception e) {
				return new Response<String>(e.getMessage(), false);
			}
		} return new Response<String>("Not authorized", false);
	}
	
	@RequestMapping(value = "/deleteVM", method = RequestMethod.POST)
	public Response<String> deleteVirtualMachine(@RequestHeader("username") String username,
												 @RequestBody MongoVirtualMachine entity) {
		if (username.equals(entity.getUsername())) {
			try {
				azure.virtualMachines().deleteByResourceGroup(entity.getGroupId(), entity.getName());
				Optional<MongoCustomer> optionalMongoCustomer = customerRepository.findById(entity.getUsername());
				if (optionalMongoCustomer.isPresent()) {
					MongoCustomer customer = optionalMongoCustomer.get();
					customer.deleteMachineId(entity.getId());
					customerRepository.save(customer);
				}
				getRepository().deleteById(entity.getId());
				return new Response<>("ok", true);
			} catch (Exception e) {
				return new Response<String>(e.getMessage(), false);
			}
		} return new Response<String>("Not authorized", false);
	}
}
