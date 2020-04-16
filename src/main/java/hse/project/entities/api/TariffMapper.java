package hse.project.entities.api;

import hse.project.entities.prototypes.MapperPrevInterface;
import hse.project.entities.prototypes.Tariff;

public class TariffMapper implements MapperPrevInterface<Tariff, TariffPreview> {
	
	@Override
	public void map(Tariff source, Tariff result) {
		result = source;
	}
	
	@Override
	public TariffPreview mapToPreview(Tariff source) {
		return new TariffPreview(source.getId(), source.getTariffName(), source.getPrice(), source.getType());
	}
}
