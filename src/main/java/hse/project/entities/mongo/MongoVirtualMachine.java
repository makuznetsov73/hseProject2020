package hse.project.entities.mongo;

import hse.project.entities.prototypes.VirtualMachine;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

public class MongoVirtualMachine implements VirtualMachine {
	
	@Id
	private String id;
	
	private String name;
	private String groupId;
	private String groupName;
	private String login;
	private String password;
	private String username;
	
	private String size;
	private String sku;
	
	private boolean on;
	private double price;
	private DateTime startDate;
	private DateTime endTime;
	private String ipAddress;
	
	private DateTime startPause;
	
	private boolean blocked;
	
	public MongoVirtualMachine(String name, String username) {
		id = name + "_" + username;
		this.name = name;
		this.username = username;
	}
	
	public MongoVirtualMachine() {}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String getGroupId() {
		return groupId;
	}
	
	@Override
	public String getGroupName() {
		return groupName;
	}
	
	@Override
	public String getLogin() {
		return login;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Override
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Override
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isOn() {
		return on;
	}
	
	public void setOn(boolean on) {
		this.on = on;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public DateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	
	public DateTime getEndTime() {
		return endTime;
	}
	
	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public DateTime getStartPause() {
		return startPause;
	}
	
	public void setStartPause(DateTime startPause) {
		this.startPause = startPause;
	}
	
	public boolean isBlocked() {
		return blocked;
	}
	
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
}
