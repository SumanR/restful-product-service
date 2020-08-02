package com.restservice.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restservice.configuration.TestConfiguration;
import com.restservice.serviceContracts.redSky.RedSkyProducts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@ActiveProfiles("dev-UnitTest")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class TestDeserialization {

    @Inject
    ObjectMapper mapper;

    @Test
    public void testDeseralization() throws IOException {

        String pathString = this.getClass().getClassLoader().getResource("redSky.json").getPath();
        String jsonResponse = new String(Files.readAllBytes(Paths.get(pathString)));
        RedSkyProducts product = mapper.readValue(jsonResponse, RedSkyProducts.class);
        System.out.println(product);
        System.out.println(product.getProduct().getItem().getProductDescription().getTitle());
        Assert.notNull(product, "the object is null");
        Assert.notNull(product.getProduct().getItem().getProductDescription().getTitle(), "'");
    }


}
