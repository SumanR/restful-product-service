package com.restservice;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.restservice")
@EnableAutoConfiguration
public class RestServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new RestServiceApplication().configure(new SpringApplicationBuilder(RestServiceApplication.class)).run(args);
    }


}
