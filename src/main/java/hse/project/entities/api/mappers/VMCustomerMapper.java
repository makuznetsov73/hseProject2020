package hse.project.entities.api.mappers;

import hse.project.entities.api.previews.CustomerPreview;
import hse.project.entities.mongo.MongoCustomer;
import hse.project.entities.prototypes.Customer;
import hse.project.entities.prototypes.MapperPrevInterface;

public class VMCustomerMapper implements MapperPrevInterface<Customer, CustomerPreview> {
	
	@Override
	public void map(Customer source, Customer result) {
		result.setBalance(source.getBalance());
		result.setBlocked(source.isBlocked());
		result.setEmail(source.getEmail());
		result.setId(source.getId());
		result.setLogin(source.getLogin());
		result.setPassword(source.getPassword());
		result.setPhoneNumber(source.getPhoneNumber());
		if (source instanceof MongoCustomer && result instanceof MongoCustomer) {
			MongoCustomer sourceRef = (MongoCustomer) source;
			MongoCustomer resultRef = (MongoCustomer) result;
			resultRef.setMachinesIds(sourceRef.getMachinesIds());
		}
	}
	
	@Override
	public CustomerPreview mapToPreview(Customer source) {
		CustomerPreview preview = new CustomerPreview();
		preview.setBalance(source.getBalance());
		preview.setId(source.getId());
		preview.setLogin(source.getLogin());
		return preview;
	}
}

