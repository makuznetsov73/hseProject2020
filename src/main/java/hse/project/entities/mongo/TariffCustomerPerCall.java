package hse.project.entities.mongo;

public class TariffCustomerPerCall extends MongoTariffCustomer {
	
	private TariffPerCallState tariffState;
	
	public TariffPerCallState getTariffState() {
		return tariffState;
	}
	
	public void setTariffState(TariffPerCallState tariffState) {
		this.tariffState = tariffState;
	}
}
