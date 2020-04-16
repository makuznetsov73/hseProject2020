package hse.project.entities.prototypes;

public enum TariffType {
    PER_CALL("PER_CALL"), PER_TIME("PER_TIME");
    
    TariffType(String tariffType) {
        this.tariffType = tariffType;
    }
    
    String tariffType;
}

