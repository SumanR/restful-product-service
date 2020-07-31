package com.casestudy.restservice.commons;

import com.casestudy.serviceContracts.redSky.RedSkyProducts;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class DeserializationUtil {

    static ObjectMapper jsonStringToPojoMapper;

    static {

        jsonStringToPojoMapper = new ObjectMapper();
        jsonStringToPojoMapper.registerModule(new JaxbAnnotationModule());
        jsonStringToPojoMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        jsonStringToPojoMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T getDeserializedObject(String key, Class<T> clazz) {
        T obj = null;
        try {

            obj = jsonStringToPojoMapper.readValue(key, clazz);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
