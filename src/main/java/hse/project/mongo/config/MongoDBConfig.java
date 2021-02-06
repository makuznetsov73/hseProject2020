package hse.project.mongo.config;

import hse.project.mongo.repository.TariffCustomerRepositoryInterface;
import hse.project.mongo.repository.TariffRepositoryInterface;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {TariffRepositoryInterface.class, TariffCustomerRepositoryInterface.class})
@Configuration
public class MongoDBConfig {

  /*@Bean
  CommandLineRunner commandLineRunner(TariffRepositoryInterface userRepository) {
    return strings -> {
      //userRepository.save(new AbstractMongoTariff(1, "Test basic", "nothing", 101, 100));
      //userRepository.save(new AbstractMongoTariff(2, "Test basic", "nothing", 102, 100));
    };
  }*/

}
