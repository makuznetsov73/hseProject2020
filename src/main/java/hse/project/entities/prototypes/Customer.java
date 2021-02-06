package hse.project.entities.prototypes;

import org.joda.time.DateTime;

import java.util.Optional;

public interface Customer {
    
    public String getName();
    
    public String getLogin();
    
    public String getPassword();
    
    public String getPhoneNumber();
    
    public String getEmail();
    
    public boolean isBlocked();
    
    public void setName(String name);
    
    public void setLogin(String login);
    
    public void setPassword(String password);
    
    public void setEmail(String email);
    
    public void setPhoneNumber(String phoneNumber);
    
    public void setBlocked(boolean blocked);
    
    public void setId(String id);
    
    public String getId();
    
    public void setBalance(double balance);
    
    public double getBalance();
    
    public void setCreationTime(DateTime time);
    
    public void setChangeTime(DateTime time);
    
    public DateTime getCreationTime();
    
    public DateTime getChangeTime();
}
