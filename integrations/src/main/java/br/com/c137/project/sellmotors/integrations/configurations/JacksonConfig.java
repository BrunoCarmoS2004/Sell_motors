package br.com.c137.project.sellmotors.integrations.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Registra o módulo para suportar LocalDate, LocalDateTime, etc.
        mapper.registerModule(new JavaTimeModule());

        // Desativa a escrita de datas como arrays numéricos [2026, 5, 8]
        // Força o formato ISO-8601 "2026-05-08T17:57:00"
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }
}