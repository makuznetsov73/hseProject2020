package hse.project.entities.mongo;

public class CustomerPerCall extends MongoCustomer {
	
	private TariffPerCallState tariffState;
	
	public TariffPerCallState getTariffState() {
		return tariffState;
	}
	
	public void setTariffState(TariffPerCallState tariffState) {
		this.tariffState = tariffState;
	}
}
