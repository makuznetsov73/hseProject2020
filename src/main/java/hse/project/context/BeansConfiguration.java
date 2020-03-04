package hse.project.context;

import hse.project.dataApi.DummyService;
import org.springframework.context.annotation.*;

@Configuration
public class BeansConfiguration {

    @Bean
    public DummyService getDataDummy() {
        return new DummyService();
    }
}
