package hse.project.controllers.admincontrollers;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import hse.project.controllers.AbstractRepositoryPrevController;
import hse.project.entities.Response;
import hse.project.entities.api.mappers.TariffMapper;
import hse.project.entities.api.mappers.VirtualMachineMapper;
import hse.project.entities.api.previews.TariffPreview;
import hse.project.entities.api.previews.VirtualMachinePreview;
import hse.project.entities.mongo.*;
import hse.project.entities.prototypes.TariffType;
import hse.project.entities.prototypes.VirtualMachine;
import hse.project.mongo.repository.TariffRepositoryInterface;
import hse.project.mongo.repository.VirtualMachineRepository;
import hse.project.utils.AzureAuth;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/admin/vmachine")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AdminVirtualMachineController
	extends AbstractRepositoryPrevController<MongoVirtualMachine, VirtualMachine, VirtualMachinePreview,
		VirtualMachineMapper, VirtualMachineRepository> {
	
	AdminVirtualMachineController(VirtualMachineRepository repository) {
		setRepository(repository);
		setMapper(new VirtualMachineMapper());
	}
	
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public Response<MongoVirtualMachine> changeVMachine(@RequestBody MongoVirtualMachine entity) {
		Optional<MongoVirtualMachine> optionalMongoVirtualMachine = getRepository().findById(entity.getId());
		if (optionalMongoVirtualMachine.isPresent()) {
			entity = getRepository().save(entity);
			return new Response<>(entity, true);
		}
		return new Response<>(null, false);
	}
}
