package com.restservice.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev-UnitTest")
@Configuration
//@ComponentScan(basePackages = {"com.casestudy.restservice" })
public class TestConfiguration {

    @Bean
    public ObjectMapper fetchObjectMapper() {
        ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JaxbAnnotationModule());
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;

    }


}
