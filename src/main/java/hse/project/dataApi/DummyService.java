package hse.project.dataApi;


import hse.project.entities.Tariff;

public class DummyService {

    public Tariff getByNumber(int number) {
        return new Tariff("Test basic", "nothing", 100, 100);
    }
}
