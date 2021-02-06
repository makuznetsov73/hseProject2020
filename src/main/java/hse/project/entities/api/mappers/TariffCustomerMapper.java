package hse.project.entities.api.mappers;

import hse.project.entities.api.previews.TariffCustomerPreview;
import hse.project.entities.prototypes.MapperPrevInterface;
import hse.project.entities.prototypes.TariffCustomer;

public class TariffCustomerMapper implements MapperPrevInterface<TariffCustomer, TariffCustomerPreview> {
	
	@Override
	public void map(TariffCustomer source, TariffCustomer result) {
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
	public TariffCustomerPreview mapToPreview(TariffCustomer source) {
		TariffCustomerPreview preview = new TariffCustomerPreview();
		preview.setBalance(source.getBalance());
		preview.setId(source.getId());
		preview.setLogin(source.getLogin());
		preview.setTariffId(source.getTariffId());
		return preview;
	}
}
