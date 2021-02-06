package hse.project.entities.api.previews;

import hse.project.entities.prototypes.TariffType;

public class TariffPreview {
	
	private String id;
	
	private String tariffName;
	
	private double price;
	
	private TariffType tariffType;
	
	public TariffPreview(String id, String name, double price, TariffType tariffType) {
		this.id = id;
		this.tariffName = name;
		this.price = price;
		this.tariffType = tariffType;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public TariffType getTariffType() {
		return tariffType;
	}
	
	public void setTariffType(TariffType tariffType) {
		this.tariffType = tariffType;
	}
	
	public String getTariffName() {
		return tariffName;
	}
	
	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}
}
