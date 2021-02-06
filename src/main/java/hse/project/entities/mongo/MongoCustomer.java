package hse.project.entities.mongo;

import hse.project.entities.prototypes.Customer;
import hse.project.entities.prototypes.IdEntity;
import hse.project.entities.prototypes.TariffType;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "Customers")
public class MongoCustomer implements Customer, IdEntity {
	
	@Id
	private String id;
	
	private String name;
	
	private String login;
	
	private String password;
	
	private double balance;
	
	private String phoneNumber;
	
	private String email;
	
	private DateTime creationTime;
	
	private DateTime changeTime;
	
	private boolean blocked;
	
	private Set<String> machinesIds;
	
	public MongoCustomer(String id, String name, String login) {
		this(name, login);
		this.id = id;
	}
	
	public MongoCustomer(String name, String login) {
		super();
		this.id = login;
		this.login = login;
		this.name = name;
	}
	
	public MongoCustomer() {
		this.creationTime = this.changeTime = DateTime.now();
	}
	
	public boolean addMachineId(String id) {
		if (machinesIds != null) {
			return machinesIds.add(id);
		} else {
			machinesIds = new HashSet<>();
			return machinesIds.add(id);
		}
	}
	
	public boolean deleteMachineId(String id) {
		if (machinesIds.contains(id)) {
			return machinesIds.remove(id);
		} else
			return false;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getLogin() {
		return login;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	@Override
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public boolean isBlocked() {
		return blocked;
	}
	
	public void setEmail(String emailNumber) {
		this.email = emailNumber;
	}
	
	public DateTime getCreationTime() {
		return creationTime;
	}
	
	public DateTime getChangeTime() {
		return changeTime;
	}
	
	public void setChangeTime(DateTime changeTime) {
		this.changeTime = changeTime;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public void setCreationTime(DateTime creationTime) {
		this.creationTime = creationTime;
	}
	
	public Set<String> getMachinesIds() {
		return machinesIds;
	}
	
	public void setMachinesIds(Set<String> machinesIds) {
		this.machinesIds = machinesIds;
	}
}
