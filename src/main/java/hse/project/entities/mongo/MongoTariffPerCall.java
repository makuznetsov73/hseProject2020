package hse.project.entities.mongo;

public class MongoTariffPerCall extends AbstractMongoTariff {
    
    private int callsAvailable;
    
    public int getCallsAvailable() {
        return callsAvailable;
    }
    
    public void setCallsAvailable(int callsAvailable) {
        this.callsAvailable = callsAvailable;
    }
}
