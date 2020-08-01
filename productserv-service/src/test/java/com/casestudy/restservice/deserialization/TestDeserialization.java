package com.casestudy.restservice.deserialization;

import com.casestudy.restservice.commons.DeserializationUtil;
import com.casestudy.serviceContracts.redSky.RedSkyProducts;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDeserialization {

    @Test
    public void testDeseralization() throws IOException {

        String pathString = this.getClass().getClassLoader().getResource("redSky.json").getPath();
        String jsonResponse = new String(Files.readAllBytes(Paths.get(pathString)));
        RedSkyProducts product = DeserializationUtil.getDeserializedObject(jsonResponse, RedSkyProducts.class);
        System.out.println(product);
        System.out.println(product.getProduct().getItem().getProductDescription().getTitle());
        Assert.notNull(product,"the object is null");
        Assert.notNull(product.getProduct().getItem().getProductDescription().getTitle(),"'");
    }


}
