package hse.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class HseApp {

    public static void main(String[] args) {
        SpringApplication.run(HseApp.class, args);
        System.out.println("Hello ");
    }
}
