package hse.project.entities.mongo;

public class TariffCustomerPerTime extends MongoTariffCustomer {
	
	private TariffPerTimeState tariffState;
	
	public TariffPerTimeState getTariffState() {
		return tariffState;
	}
	
	public void setTariffState(TariffPerTimeState tariffState) {
		this.tariffState = tariffState;
	}
}
