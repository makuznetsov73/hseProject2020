package hse.project.controllers;

import hse.project.entities.Response;
import hse.project.entities.api.TariffMapper;
import hse.project.entities.api.TariffPreview;
import hse.project.entities.mongo.AbstractMongoTariff;
import hse.project.entities.mongo.MongoTariffPerCall;
import hse.project.entities.mongo.MongoTariffPerTime;
import hse.project.entities.prototypes.Tariff;
import hse.project.entities.prototypes.TariffType;
import hse.project.mongo.repository.TariffRepositoryInterface;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/admin/tariff")
@CrossOrigin(origins = "http://localhost:4200")
public class TariffController
    extends AbstractRepositoryPrevController<AbstractMongoTariff, Tariff, TariffPreview, TariffMapper, TariffRepositoryInterface> {

    TariffController(TariffRepositoryInterface tariffRepository) {
        setRepository(tariffRepository);
        setMapper(new TariffMapper());
    }
    
    @RequestMapping(value = "/create/time", method = RequestMethod.POST)
    public Response<TariffPreview> createTariffPerTime(@RequestBody MongoTariffPerTime entity) {
        entity.setType(TariffType.PER_TIME);
        entity = getRepository().insert(entity);
        TariffPreview preview = getMapper().mapToPreview(entity);
        return new Response<>(preview, true);
    }
    
    @RequestMapping(value = "/create/call", method = RequestMethod.POST)
    public Response<TariffPreview> createTariffPerCall(@RequestBody MongoTariffPerCall entity) {
        entity.setType(TariffType.PER_CALL);
        entity = getRepository().insert(entity);
        TariffPreview preview = getMapper().mapToPreview(entity);
        return new Response<>(preview, true);
    }
    
    @RequestMapping(value = "/change/time", method = RequestMethod.POST)
    public Response<MongoTariffPerTime> changeTariffPerTime(@RequestBody MongoTariffPerTime entity) {
        AbstractMongoTariff currentTariff = getRepository().findById(entity.getId()).get();
        entity.setType(TariffType.PER_TIME);
        entity.setCreationTime(currentTariff.getCreationTime());
        entity.setChangeTime(DateTime.now());
        entity = getRepository().save(entity);
        return new Response<>(entity, true);
    }
    
    @RequestMapping(value = "/change/call", method = RequestMethod.POST)
    public Response<MongoTariffPerCall> changeTariffPerCall(@RequestBody MongoTariffPerCall entity) {
        AbstractMongoTariff currentTariff = getRepository().findById(entity.getId()).get();
        entity.setType(TariffType.PER_CALL);
        entity.setCreationTime(currentTariff.getCreationTime());
        entity.setChangeTime(DateTime.now());
        entity = getRepository().save(entity);
        return new Response<>(entity, true);
    }
}
