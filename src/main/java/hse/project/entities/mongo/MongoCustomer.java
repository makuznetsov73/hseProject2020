package hse.project.entities.mongo;

import hse.project.entities.prototypes.*;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    
    private String tariffId;
    
    private String tariffName;
    
    private TariffType tariffType;
    
    private DateTime creationTime;
    
    private DateTime changeTime;
    
    private boolean blocked;
    
    private boolean newTariff;
    
    public MongoCustomer(String id, String name, String login) {
        this(name, login);
        this.id = id;
    }
    
    public MongoCustomer(String name, String login) {
        super();
        this.login = login;
        this.name = name;
    }
    
    public MongoCustomer() {
        this.creationTime = this.changeTime = DateTime.now();
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
    public void setTariffId(String tariffId) {
        this.tariffId = tariffId;
    }
    
    @Override
    public void setTariffType(TariffType tariffType) {
        this.tariffType = tariffType;
    }
    
    @Override
    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }
    
    @Override
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public String getTariffId() {
        return this.tariffId;
    }
    
    @Override
    public String getTariffName() {
        return this.tariffName;
    }
    
    @Override
    public TariffType getTariffType() {
        return this.tariffType;
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
    
    public boolean isNewTariff() {
        return newTariff;
    }
    
    public void setNewTariff(boolean newTariff) {
        this.newTariff = newTariff;
    }
}
