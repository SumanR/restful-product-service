package com.casestudy.restservice.configuration;

import com.casestudy.restservice.resources.ProductResourceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConf extends ResourceConfig {
    public JerseyConf() {
        register(ProductResourceImpl.class);
    }
}