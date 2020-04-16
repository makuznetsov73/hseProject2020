package hse.project.controllers;

import com.google.common.collect.Lists;
import hse.project.entities.Response;
import hse.project.entities.api.EntitiesPage;
import hse.project.entities.prototypes.MapperInterface;
import hse.project.entities.prototypes.MapperPrevInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepositoryPrevController<T extends A, A, P, M extends MapperPrevInterface<A, P>,  R extends MongoRepository<T, String>>
	extends AbstractRepositoryController<T, A, M, R> {
	
	@RequestMapping(value = "/single/prev/{id}", method = RequestMethod.GET)
	public Response<P> getEntityByIdPrev(@PathVariable("id") String id) {
		Optional<T> entity = getRepository().findById(id);
		return entity.map(t -> new Response<>(getMapper().mapToPreview(t), true)).orElseGet(Response::new);
	}
	
	@RequestMapping(value = "/all/prev", method = RequestMethod.GET, produces = "application/json")
	public Response<EntitiesPage<List<P>>> getAllPrev(@RequestParam int page) {
		Pageable pageable = PageRequest.of(page, 30);
		Page<T> pageList = getRepository().findAll(pageable);
		List<P> result = new ArrayList<>();
		for (T el : pageList.getContent()) {
			result.add(getMapper().mapToPreview(el));
		}
		return new Response<>(new EntitiesPage<>(page, pageList.getTotalPages(),
			result), true);
	}
}
