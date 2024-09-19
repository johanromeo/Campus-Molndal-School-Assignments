package com.jromeo.internshipbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Author: Johan Romeo
 */

@SpringBootApplication
public class InternshipBackendApplication {

    /**
     * Usage of ModelMapper inside InternshipService
     */
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(InternshipBackendApplication.class, args);
    }
}
