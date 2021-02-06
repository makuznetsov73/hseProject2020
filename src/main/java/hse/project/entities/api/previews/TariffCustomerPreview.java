package hse.project.entities.api.previews;

public class TariffCustomerPreview {
	
	private String id;
	
	private String login;
	
	private String tariffId;
	
	private double balance;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getTariffId() {
		return tariffId;
	}
	
	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}
}
