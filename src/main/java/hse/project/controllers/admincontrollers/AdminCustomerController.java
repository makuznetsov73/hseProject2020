package hse.project.controllers.admincontrollers;

import hse.project.controllers.AbstractRepositoryPrevController;
import hse.project.entities.Response;
import hse.project.entities.api.CustomerMapper;
import hse.project.entities.api.CustomerPreview;
import hse.project.entities.mongo.*;
import hse.project.entities.prototypes.Customer;
import hse.project.entities.prototypes.TariffType;
import hse.project.mongo.repository.CustomerRepositoryInterface;
import hse.project.mongo.repository.TariffRepositoryInterface;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/admin/customer")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AdminCustomerController
    extends AbstractRepositoryPrevController<MongoCustomer, Customer, CustomerPreview, CustomerMapper, CustomerRepositoryInterface> {
    
    private TariffRepositoryInterface tariffRepository;
    
    AdminCustomerController(CustomerRepositoryInterface customerRepository, TariffRepositoryInterface tariffRepository) {
        setRepository(customerRepository);
        setMapper(new CustomerMapper());
        this.tariffRepository = tariffRepository;
    }
    
    @RequestMapping(value = "/create/customer", method = RequestMethod.POST)
    public Response<CustomerPreview> createCustomer(@RequestBody MongoCustomer entity) {
        Optional<MongoCustomer> optionalMongoCustomer = getRepository().findById(entity.getId());
        if (optionalMongoCustomer.isPresent())
            return new Response<>();
        else {
            MongoCustomer newEntity = entity;
            if (entity.getTariffId() != null) {
                AbstractMongoTariff tariff = tariffRepository.findById(entity.getTariffId()).get();
                if (tariff instanceof MongoTariffPerCall) {
                    newEntity = new CustomerPerCall();
                    getMapper().map(entity, newEntity);
                    ((CustomerPerCall) newEntity).setTariffState(new TariffPerCallState((MongoTariffPerCall) tariff));
                } else if (tariff instanceof MongoTariffPerTime) {
                    newEntity = new CustomerPerTime();
                    getMapper().map(entity, newEntity);
                    ((CustomerPerTime) newEntity).setTariffState(new TariffPerTimeState((MongoTariffPerTime) tariff));
                }
            }
            newEntity.setNewTariff(false);
            getRepository().insert(newEntity);
            return new Response<>(null, true);
        }
    }
    
    @RequestMapping(value = "/change/time", method = RequestMethod.POST)
    public Response<MongoCustomer> changeCustomerTime(@RequestBody CustomerPerTime entity) {
        if (!entity.isNewTariff()) {
            entity = getRepository().save(entity);
            return new Response<>(entity, true);
        } else {
            MongoCustomer newEntity = entity;
            AbstractMongoTariff tariff = tariffRepository.findById(entity.getTariffId()).get();
            newEntity = new CustomerPerTime();
            getMapper().map(entity, newEntity);
            ((CustomerPerTime) newEntity).setTariffState(new TariffPerTimeState((MongoTariffPerTime) tariff));
            newEntity.setNewTariff(false);
            newEntity = getRepository().save(newEntity);
            return new Response<>(newEntity, true);
        }
    }
    
    @RequestMapping(value = "/change/call", method = RequestMethod.POST)
    public Response<MongoCustomer> changeCustomerCall(@RequestBody CustomerPerCall entity) {
        if (!entity.isNewTariff()) {
            entity = getRepository().save(entity);
            return new Response<>(entity, true);
        } else {
            MongoCustomer newEntity = entity;
            AbstractMongoTariff tariff = tariffRepository.findById(entity.getTariffId()).get();
            newEntity = new CustomerPerCall();
            getMapper().map(entity, newEntity);
            ((CustomerPerCall) newEntity).setTariffState(new TariffPerCallState((MongoTariffPerCall) tariff));
            newEntity.setNewTariff(false);
            newEntity = getRepository().save(newEntity);
            return new Response<>(newEntity, true);
        }
    }
}
