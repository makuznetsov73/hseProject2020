package hse.project.entities.mongo;

public class CustomerPerTime extends MongoCustomer {
	
	private TariffPerTimeState tariffState;
	
	public TariffPerTimeState getTariffState() {
		return tariffState;
	}
	
	public void setTariffState(TariffPerTimeState tariffState) {
		this.tariffState = tariffState;
	}
}
