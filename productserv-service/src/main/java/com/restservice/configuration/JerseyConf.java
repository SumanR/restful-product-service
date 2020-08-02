package com.restservice.configuration;

import com.restservice.resources.ProductResourceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConf extends ResourceConfig {
    public JerseyConf() {
        register(ProductResourceImpl.class);
    }
}