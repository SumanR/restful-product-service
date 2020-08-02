package com.restservice.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.restservice.entities.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class ProductServiceConfiguration {

    @Value(value = "${redis.hostname}")
    private String redisHostname;

    @Value(value = "${redis.port}")
    private int redisPort;


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHostname, redisPort);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Product> productTemplate() {
        RedisTemplate<String, Product> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }

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
