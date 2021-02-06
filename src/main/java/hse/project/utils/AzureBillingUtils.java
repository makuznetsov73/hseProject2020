package hse.project.utils;

import hse.project.entities.mongo.MongoCustomer;
import hse.project.entities.mongo.MongoVirtualMachine;
import hse.project.mongo.repository.VMCustomerRepositoryInterface;
import hse.project.mongo.repository.VirtualMachineRepository;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.regex.Matcher;

public class AzureBillingUtils {
	
	private static boolean changedData = false;
	
	private static DateTime limit = new DateTime();
	
	private static Map<String, Double> skuPriceK;
	private static Map<String, Double> sizePriceK;
	
	private static VMCustomerRepositoryInterface customerRepository;
	private static VirtualMachineRepository virtualMachineRepository;
	
	static
	{
		skuPriceK = new HashMap<>();
		sizePriceK = new HashMap<>();
		
		skuPriceK.put("2012-Datacenter", 1.0);
		skuPriceK.put("2012-R2-Datacenter", 1.1);
		skuPriceK.put("2016-Datacenter", 1.25);
		skuPriceK.put("2019-Datacenter", 1.35);
		
		sizePriceK.put("Standard_B1ls", 1.0);
		sizePriceK.put("Standard_B1ms", 1.1);
		sizePriceK.put("Standard_B2ms", 1.15);
		sizePriceK.put("Standard_B20ms", 1.3);
		sizePriceK.put("Standard_DS1_v2", 1.2);
		sizePriceK.put("Standard_DS5_v2", 1.4);
		sizePriceK.put("Standard_D48s_v3", 1.7);
	}
	
	public static void billingProcess() throws InterruptedException {
		Pageable pageable = PageRequest.of(1, 30);
		while (true) {
			Page<MongoVirtualMachine> pageList = virtualMachineRepository.findAllByEndTimeBefore(DateTime.now(), pageable);
			
			for (MongoVirtualMachine vm : pageList.getContent()) {
				Optional<MongoCustomer> optionalCustomer = customerRepository.findById(vm.getUsername());
				if (optionalCustomer.isPresent()) {
					MongoCustomer customer = optionalCustomer.get();
					if (customer.getBalance() >= vm.getPrice() && vm.isOn() && !vm.isBlocked()) {
						customer.setBalance(customer.getBalance() - vm.getPrice());
						vm.setStartDate(DateTime.now());
						vm.setEndTime(DateTime.now().plusDays(7));
						virtualMachineRepository.save(vm);
						customerRepository.save(customer);
					} else {
						vm.setBlocked(true);
						vm.setEndTime(DateTime.now().plusYears(20));
						blockVirtualMachine(vm);
						virtualMachineRepository.save(vm);
						customerRepository.save(customer);
					}
				}
			}
			Thread.sleep(100000L);
		}
	}
	
	private static void blockVirtualMachine(MongoVirtualMachine vm) {
		com.microsoft.azure.management.compute.VirtualMachine azureVm = AzureAuth.getAzure().virtualMachines()
			.getByResourceGroup(vm.getGroupId(), vm.getName());
		azureVm.deallocate();
	}
	
	public static boolean isChangedData() {
		return changedData;
	}
	
	public static void setChangedData(boolean changedData) {
		AzureBillingUtils.changedData = changedData;
	}
	
	public static Map<String, Double> getSkuPriceK() {
		return skuPriceK;
	}
	
	public static void setSkuPriceK(Map<String, Double> skuPriceK) {
		AzureBillingUtils.skuPriceK = skuPriceK;
	}
	
	public static Map<String, Double> getSizePriceK() {
		return sizePriceK;
	}
	
	public static void setSizePriceK(Map<String, Double> sizePriceK) {
		AzureBillingUtils.sizePriceK = sizePriceK;
	}
	
	public static DateTime getLimit() {
		return limit;
	}
	
	public static void setLimit(DateTime limit) {
		AzureBillingUtils.limit = limit;
	}
	
	public static VMCustomerRepositoryInterface getCustomerRepository() {
		return customerRepository;
	}
	
	public static void setCustomerRepository(VMCustomerRepositoryInterface customerRepository) {
		AzureBillingUtils.customerRepository = customerRepository;
	}
	
	public static VirtualMachineRepository getVirtualMachineRepository() {
		return virtualMachineRepository;
	}
	
	public static void setVirtualMachineRepository(VirtualMachineRepository virtualMachineRepository) {
		AzureBillingUtils.virtualMachineRepository = virtualMachineRepository;
	}
}
