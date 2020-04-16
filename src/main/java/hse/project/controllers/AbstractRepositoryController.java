package hse.project.controllers;

import com.google.common.collect.Lists;
import hse.project.entities.Response;
import hse.project.entities.api.EntitiesPage;
import hse.project.entities.mongo.AbstractMongoTariff;
import hse.project.entities.prototypes.MapperInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public abstract class AbstractRepositoryController<T extends A, A, M extends MapperInterface<A>, R extends MongoRepository<T, String>> {
    
    private R repository;
    
    private M mapper;
    
    @RequestMapping(value = "/single/full/{id}", method = RequestMethod.GET)
    public Response<T> getEntityById(@PathVariable("id") String id) {
        Optional<T> entity = repository.findById(id);
        return entity.map(t -> new Response<>(t, true)).orElseGet(Response::new);
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createEntity(@RequestBody T entity) {
    
    }
    
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public void changeEntity(@RequestBody T entity) {
    
    }
    
    @RequestMapping(value = "/all/full", method = RequestMethod.GET)
    public Response<EntitiesPage<List<T>>> getAll(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 30);
        Page<T> pageList = repository.findAll(pageable);
        return new Response<>(new EntitiesPage<List<T>>(page, pageList.getTotalPages(),
            Lists.newArrayList(pageList)), true);
    }
    
    public R getRepository() {
        return repository;
    }
    
    public M getMapper() {
        return mapper;
    }
    
    public void setRepository(R repository) {
        this.repository = repository;
    }
    
    public void setMapper(M mapper) {
        this.mapper = mapper;
    }
}
