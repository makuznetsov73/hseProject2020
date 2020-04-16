package hse.project.mongo.repository;

import hse.project.entities.mongo.AbstractMongoTariff;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TariffRepositoryInterface extends MongoRepository<AbstractMongoTariff, String> {

}
