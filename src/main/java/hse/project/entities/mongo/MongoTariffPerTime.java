package hse.project.entities.mongo;

import org.joda.time.Period;

public class MongoTariffPerTime extends AbstractMongoTariff{

    private Period timeAvailable;
    
    private int timeAvailableDay;
    
    public Period getTimeAvailable() {
        return timeAvailable;
    }
    
    public void setTimeAvailable(Period timeAvailable) {
        this.timeAvailable = timeAvailable;
    }
    
    public int getTimeAvailableDay() {
        return timeAvailableDay;
    }
    
    public void setTimeAvailableDay(int timeAvailableDay) {
        this.timeAvailableDay = timeAvailableDay;
    }
}
