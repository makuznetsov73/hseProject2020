package hse.project.controllers;

import hse.project.dataApi.DummyService;
import hse.project.entities.Tariff;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/tariff")
public class TariffController {

    private DummyService dummyService;

    TariffController(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public Tariff getTariffByNumber(@RequestParam int id) {
        return dummyService.getByNumber(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createSimpleTariff(@RequestBody Tariff tariff) {

    }
}
