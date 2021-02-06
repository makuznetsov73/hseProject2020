package hse.project.mongo.repository;

import hse.project.entities.mongo.MongoCustomer;
import hse.project.entities.mongo.MongoTariffCustomer;
import hse.project.entities.prototypes.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VMCustomerRepositoryInterface extends MongoRepository<MongoCustomer, String> {
	
	Customer findByLogin(String login);
}