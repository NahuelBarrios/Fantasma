package com.fantasma.config;

import com.fantasma.repository.EntidadUnoRepository;
import com.fantasma.service.EntidadUnoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EntidadUnoService entidadUnoService(EntidadUnoRepository entidadUnoRepository){
        return new EntidadUnoService(entidadUnoRepository);
    }
}
