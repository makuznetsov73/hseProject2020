package hse.project.mongo.repository;

import hse.project.entities.mongo.MongoVirtualMachine;
import hse.project.entities.prototypes.Customer;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VirtualMachineRepository extends MongoRepository<MongoVirtualMachine, String> {
	
	List<MongoVirtualMachine> findByUsername(String username);
	
	void deleteById(String id);
	
	Page<MongoVirtualMachine> findAllByEndTimeBefore(DateTime time, Pageable pageable);
}
