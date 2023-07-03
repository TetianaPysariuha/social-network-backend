package org.finalproject;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.finalproject.config.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@SpringBootApplication
public class SocialNetworkApplication implements ApplicationRunner {


    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

     @Autowired

    AuditorAwareImpl auditorAware;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("http://localhost:9000/swagger-ui/index.html \n");

        System.out.println("http://localhost:9000/h2-console");
    }

   @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        return mapper;
    }

}