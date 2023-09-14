package org.finalproject;


import org.finalproject.config.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Import({AuditorAwareImpl.class})
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
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

   @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mapper;
    }

}