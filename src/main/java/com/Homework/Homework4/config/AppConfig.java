package com.Homework.Homework4.config;

import com.Homework.Homework4.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "getAuditorAware")
@Configuration
public class AppConfig {

    @Bean
    ModelMapper getModalMapper(){return new ModelMapper();}

    @Bean
    AuditorAware<String> getAuditorAware(){return new AuditorAwareImpl();}
}
