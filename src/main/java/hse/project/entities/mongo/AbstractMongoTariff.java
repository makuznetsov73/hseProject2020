package hse.project.entities.mongo;

import hse.project.entities.prototypes.IdEntity;
import hse.project.entities.prototypes.Tariff;
import hse.project.entities.prototypes.TariffType;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tariffs")
public abstract class AbstractMongoTariff implements Tariff, IdEntity {

    @Id
    private String id;

    private String tariffName;

    private double price = 100;

    private String description;
    
    private TariffType type = TariffType.PER_CALL;
    
    private DateTime creationTime;
    
    private DateTime changeTime;

    public AbstractMongoTariff(String name, String description, double price) {
        super();
        this.description = description;
        this.tariffName = name;
        this.price = price;
    }

    public AbstractMongoTariff(String id, String name, String description, double price) {
        super();
        this.id = id;
        this.description = description;
        this.tariffName = name;
        this.price = price;
    }

    public AbstractMongoTariff() {
        this.creationTime = this.changeTime = DateTime.now();
    }

    public String getTariffName() {
        return tariffName;
    }

    public double getPrice() {
        return price;
    }
    
    @Override
    public TariffType getType() {
        return type;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public DateTime getCreationTime() {
        return creationTime;
    }
    
    @Override
    public DateTime getChangeTime() {
        return changeTime;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    @Override
    public void setType(TariffType type) {
        this.type = type;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public void setCreationTime(DateTime time) {
        this.creationTime = time;
    }
    
    @Override
    public void setChangeTime(DateTime time) {
        this.changeTime = time;
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public void setId(String id) {
        this.id = id;
    }
}
