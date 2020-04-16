package hse.project.entities.mongo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hse.project.entities.prototypes.TariffState;

public class TariffPerCallState implements TariffState {
	
	private int currentCallsAvailable;
	
	@JsonProperty("isBlocked")
	private boolean isBlocked;
	
	public TariffPerCallState(MongoTariffPerCall tariff) {
		currentCallsAvailable = tariff.getCallsAvailable();
	}
	
	@JsonCreator
	public TariffPerCallState() {}
	
	public int getCurrentCallsAvailable() {
		return currentCallsAvailable;
	}
	
	@Override
	public boolean requestHandle(Object ...args) {
		for (Object arg: args) {
			if (arg instanceof Integer) {
				if (currentCallsAvailable >= (Integer)arg) {
					currentCallsAvailable -= (Integer) arg;
					return true;
				} else {
					return false;
				}
			} else {
				if (currentCallsAvailable >= 1) {
					currentCallsAvailable--;
					return true;
				} else {
					isBlocked = true;
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean isBlocked() {
		return isBlocked;
	}
	
	public void setCurrentCallsAvailable(int currentCallsAvailable) {
		this.currentCallsAvailable = currentCallsAvailable;
	}
	
	public void setBlocked(boolean isBlocked) {
		isBlocked = isBlocked;
	}
}
