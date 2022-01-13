package pl.berenhard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class StudentsListAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentsListAppApplication.class, args);
    }

}
