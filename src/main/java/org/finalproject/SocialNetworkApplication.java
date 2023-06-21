package org.finalproject;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialNetworkApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("http://localhost:9000/swagger-ui/index.html \n");
        System.out.println("http://localhost:9000/h2-console");
    }
}