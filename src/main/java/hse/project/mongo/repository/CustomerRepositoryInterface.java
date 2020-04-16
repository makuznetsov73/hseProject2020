package hse.project.mongo.repository;

import hse.project.entities.mongo.MongoCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepositoryInterface extends MongoRepository<MongoCustomer, String> {
}
