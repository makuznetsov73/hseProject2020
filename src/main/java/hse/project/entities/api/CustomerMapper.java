package hse.project.entities.api;

import hse.project.entities.prototypes.Customer;
import hse.project.entities.prototypes.MapperInterface;
import hse.project.entities.prototypes.MapperPrevInterface;

public class CustomerMapper implements MapperPrevInterface<Customer, CustomerPreview> {
	
	@Override
	public void map(Customer source, Customer result) {
		result.setBalance(source.getBalance());
		result.setBlocked(source.isBlocked());
		result.setEmail(source.getEmail());
		result.setId(source.getId());
		result.setLogin(source.getLogin());
		result.setPassword(source.getPassword());
		result.setPhoneNumber(source.getPhoneNumber());
		result.setTariffId(source.getTariffId());
		result.setTariffName(source.getTariffName());
		result.setTariffType(source.getTariffType());
	}
	
	@Override
	public CustomerPreview mapToPreview(Customer source) {
		CustomerPreview preview = new CustomerPreview();
		preview.setBalance(source.getBalance());
		preview.setId(source.getId());
		preview.setLogin(source.getLogin());
		preview.setTariffId(source.getTariffId());
		return preview;
	}
}
