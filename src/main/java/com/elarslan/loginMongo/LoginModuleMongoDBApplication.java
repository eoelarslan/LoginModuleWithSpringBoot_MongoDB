package com.elarslan.loginMongo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Login Module w/ Spring Framework usgin MongoDB
 */
@EnableMongoRepositories
@SpringBootApplication
public class LoginModuleMongoDBApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginModuleMongoDBApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
