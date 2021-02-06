package hse.project.entities.prototypes;

public interface TariffCustomer extends Customer {
	
	public String getTariffId();
	
	public String getTariffName();
	
	public TariffType getTariffType();
	
	public void setTariffId(String tariffId);
	
	public void setTariffType(TariffType tariffType);
	
	public void setTariffName(String tariffName);
}
