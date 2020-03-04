package hse.project.entities;

public class Tariff {

    private String name = "Basic";

    private double price = 100;

    private String description;

    private int callsAvailable = 100;

    public Tariff(String name, String description, double price, int callsAvailable) {
        this.callsAvailable = callsAvailable;
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getCallsAvailable() {
        return callsAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCallsAvailable(int callsAvailable) {
        this.callsAvailable = callsAvailable;
    }
}
