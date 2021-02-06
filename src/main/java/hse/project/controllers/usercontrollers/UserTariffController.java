package hse.project.controllers.usercontrollers;

import hse.project.controllers.AbstractRepositoryPrevController;
import hse.project.entities.api.mappers.TariffMapper;
import hse.project.entities.api.previews.TariffPreview;
import hse.project.entities.mongo.AbstractMongoTariff;
import hse.project.entities.prototypes.Tariff;
import hse.project.mongo.repository.TariffRepositoryInterface;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/tariff")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class UserTariffController extends AbstractRepositoryPrevController<AbstractMongoTariff, Tariff, TariffPreview, TariffMapper, TariffRepositoryInterface> {
	
	UserTariffController(TariffRepositoryInterface tariffRepository) {
		setRepository(tariffRepository);
		setMapper(new TariffMapper());
	}
}
