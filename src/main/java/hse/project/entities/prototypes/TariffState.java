package hse.project.entities.prototypes;

public interface TariffState {
	
	public boolean requestHandle(Object... args);
	
	public boolean isBlocked();
}
