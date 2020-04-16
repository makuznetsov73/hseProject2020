package hse.project.entities.mongo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hse.project.entities.prototypes.TariffState;
import org.joda.time.DateTime;

public class TariffPerTimeState implements TariffState {
	
	private DateTime startTime;
	
	private DateTime endTime;
	
	@JsonProperty("isBlocked")
	private boolean isBlocked;
	
	public TariffPerTimeState(MongoTariffPerTime tariff) {
		startTime = DateTime.now();
		endTime = startTime.plusDays(tariff.getTimeAvailableDay());
		isBlocked = false;
	}
	
	@JsonCreator
	public TariffPerTimeState() {}
	
	public DateTime getStartTime() {
		return startTime;
	}
	
	public DateTime getEndTime() {
		return endTime;
	}
	
	@Override
	public boolean requestHandle(Object... args) {
		if (DateTime.now().getMillis() < endTime.getMillis()) {
			return true;
		} else {
			isBlocked = true;
			return false;
		}
	}
	
	@Override
	public boolean isBlocked() {
		return isBlocked;
	}
	
	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
	
	public void setBlocked(boolean isBlocked) {
		isBlocked = isBlocked;
	}
}

