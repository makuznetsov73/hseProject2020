package hse.project.controllers.usercontrollers;

import com.mongodb.Mongo;
import hse.project.entities.Response;
import hse.project.entities.api.mappers.VMCustomerMapper;
import hse.project.entities.mongo.AbstractMongoTariff;
import hse.project.entities.mongo.MongoCustomer;
import hse.project.mongo.repository.VMCustomerRepositoryInterface;
import hse.project.mongo.repository.VirtualMachineRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user/customer")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class UserCustomerController  {
	
	private VMCustomerRepositoryInterface repository;
	
	private VMCustomerMapper mapper;
	
	public UserCustomerController(VMCustomerRepositoryInterface repository) {
		this.repository = repository;
		mapper = new VMCustomerMapper();
	}
	
	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public Response<MongoCustomer> getCustomer(@RequestHeader("username") String username) {
		Optional<MongoCustomer> customer = repository.findById(username);
		return customer.map(t -> new Response<>(t, true)).orElseGet(Response::new);
	}
	
	@RequestMapping(value = "/saveAccount", method = RequestMethod.POST)
	public Response<MongoCustomer> change(@RequestBody MongoCustomer customer) {
		MongoCustomer customerMongo = repository.findById(customer.getId()).get();
		mapper.map(customer, customerMongo);
		repository.save(customerMongo);
		return new Response<>(customer, true);
	}
}
