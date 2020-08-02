package com.casestudy.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class TestProductService {

    private static Logger logger = LoggerFactory.getLogger(TestProductService.class);


    private WebTarget productServiceClient;

    private Properties dataproperties;

    public static String postURL = "http://localhost:8080/";

    @BeforeClass
    public void initialize() throws Exception {

        dataproperties = new Properties();
        try (Reader reader = new BufferedReader(new InputStreamReader(
                TestProductService.class.getClassLoader().getResourceAsStream("productInfo.properties"),
                "UTF-8"))) {
            dataproperties.load(reader);
        }

        Client client = ClientBuilder.newClient();
        productServiceClient = client.target(postURL);

    }

    @Test
    public void testGetProducts() {
        Response response = productServiceClient.path("products/" + dataproperties.getProperty("GET_PRODUCT_ID") + "/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response != null && response.getStatus() == Response.Status.OK.getStatusCode()) {
            Reporter.log("Product get call passed with status " + response.getStatus(), true);
            String stringResponse = response.readEntity(String.class); //ProductPricingInformation
            Reporter.log(stringResponse, true);
            Assert.assertTrue(stringResponse.equals(dataproperties.getProperty("GET_PRODUCT_RESPONSE")));
        } else {
            logger.info("Product get call failed with status ", response.getStatus());
            Reporter.log("Product get call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
            Assert.fail();
        }

    }

    @Test
    public void testGetProductsFailure() {
        Response response = productServiceClient.path("products/" + dataproperties.getProperty("GET_PRODUCT_ID") + "99/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response != null && response.getStatus() == Response.Status.OK.getStatusCode()) {
            Reporter.log("Product get call passed with status " + response.getStatus(), true);
            Assert.fail();
        } else {
            logger.info("Product get call failed with status ", response.getStatus());
            Reporter.log("Product get call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
        }

    }

    @Test
    public void updateGetProducts() {

        String request = dataproperties.getProperty("UPDATE_REQUEST_BODY");
        Response response = productServiceClient.path("products/" + dataproperties.getProperty("UPDATE_PRODUCT_ID") + "/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(request, MediaType.APPLICATION_JSON));
        if (response != null && response.getStatus() == 204) {
            Reporter.log("Product update call passed with status " + response.getStatus(), true);
        } else {
            logger.info("Product update call failed with status ", response.getStatus());
            Reporter.log("Product update call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
            Assert.fail();
        }

    }


    @Test
    public void createProduct() {
        String request = dataproperties.getProperty("CREATE_REQUEST_BODY");
        Response response = productServiceClient.path("products/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(request, MediaType.APPLICATION_JSON));
        if (response != null && response.getStatus() == 201) {
            Reporter.log("Product create call passed with status " + response.getStatus(), true);
        } else {
            logger.info("Product get call failed with status ", response.getStatus());
            Reporter.log("Product get call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
            Assert.fail();
        }

    }

    @Test
    public void deleteProduct() {
        Response response = productServiceClient.path("products/" + dataproperties.getProperty("DELETE_PRODUCT_ID") + "/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).delete();
        if (response != null && response.getStatus() == 204) {
            Reporter.log("Product get call passed with status " + response.getStatus(), true);
        } else {
            logger.info("Product get call failed with status ", response.getStatus());
            Reporter.log("Product get call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
            Assert.fail();
        }

    }

}
