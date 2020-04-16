package hse.project.entities.prototypes;

import org.joda.time.DateTime;

public interface Tariff extends IdEntity {
    
    public String getTariffName();
    
    public String getDescription();
    
    public DateTime getCreationTime();
    
    public DateTime getChangeTime();
    
    public double getPrice();
    
    public TariffType getType();
    
    public void setTariffName(String tariffName);
    
    public void setDescription(String name);
    
    public void setCreationTime(DateTime time);
    
    public void setChangeTime(DateTime time);
    
    public void setPrice(double price);
    
    public void setType(TariffType type);
}
