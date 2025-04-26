package com.example.demo1.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    //Bean is Created so that we do not have to create object of ModelMapper everytime
    @Bean
    public ModelMapper getModelMapper() {

        return new ModelMapper();
    }
}
