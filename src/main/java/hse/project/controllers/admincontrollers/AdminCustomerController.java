package hse.project.controllers.admincontrollers;

import hse.project.controllers.AbstractRepositoryPrevController;
import hse.project.entities.Response;
import hse.project.entities.api.previews.CustomerPreview;
import hse.project.entities.api.previews.TariffCustomerPreview;
import hse.project.entities.api.mappers.VMCustomerMapper;
import hse.project.entities.mongo.*;
import hse.project.entities.prototypes.Customer;
import hse.project.mongo.repository.TariffRepositoryInterface;
import hse.project.mongo.repository.VMCustomerRepositoryInterface;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/admin/customer")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AdminCustomerController
    extends AbstractRepositoryPrevController<MongoCustomer, Customer, CustomerPreview, VMCustomerMapper, VMCustomerRepositoryInterface> {
    
    private TariffRepositoryInterface tariffRepository;
    
    AdminCustomerController(VMCustomerRepositoryInterface customerRepository, TariffRepositoryInterface tariffRepository) {
        setRepository(customerRepository);
        setMapper(new VMCustomerMapper());
        this.tariffRepository = tariffRepository;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response<TariffCustomerPreview> createCustomer(@RequestBody MongoCustomer entity) {
        Optional<MongoCustomer> optionalMongoCustomer = getRepository().findById(entity.getId());
        if (optionalMongoCustomer.isPresent())
            return new Response<>();
        else {
            MongoCustomer newEntity = entity;
            getRepository().insert(newEntity);
            return new Response<>(null, true);
        }
    }
    
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public Response<MongoCustomer> changeCustomer(@RequestBody MongoCustomer entity) {
        Optional<MongoCustomer> optionalMongoCustomer = getRepository().findById(entity.getId());
        if (optionalMongoCustomer.isPresent()) {
            MongoCustomer newEntity = optionalMongoCustomer.get();
            getMapper().map(entity, newEntity);
            newEntity = getRepository().save(newEntity);
            return new Response<>(newEntity, true);
        } else {
            return new Response<>(entity, false);
        }
    }
}
