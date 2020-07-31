package com.casestudy.restservice;

import com.casestudy.serviceContracts.productserv.PricingInformationProduct;
import com.casestudy.serviceContracts.productserv.ProductPricing;
import com.casestudy.serviceContracts.productserv.ProductPricingInformationError;
import com.casestudy.serviceContracts.redSky.RedSkyProducts;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        Response response = productServiceClient.path("product/" + dataproperties.getProperty("GET_PRODUCT_ID") + "/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response != null && response.getStatus() == Response.Status.OK.getStatusCode()) {
            Reporter.log("Product get call passed with status " + response.getStatus(), true);
            PricingInformationProduct pricingInformationProduct = response.readEntity(PricingInformationProduct.class);
            Reporter.log(pricingInformationProduct.toString(), true);
            Assert.assertTrue(pricingInformationProduct.getId().equals(dataproperties.getProperty("GET_PRODUCT_ID")));
        } else {
            logger.info("Product get call failed with status ", response.getStatus());
            Reporter.log("Product get call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
            ProductPricingInformationError error = response.readEntity(ProductPricingInformationError.class);
            logger.info("Product get call failed with status ", error.toString());
            Reporter.log("Product get call failed with status " + error.toString(), true);
            Assert.fail();
        }

    }

    // @Test
    public void updateGetProducts() {

        String request = dataproperties.getProperty("UPDATE_REQUEST_BODY");
        //PricingInformationProduct product = DeserializationUtil.getDeserializedObject(request, PricingInformationProduct.class);
        Response response = productServiceClient.path("product/" + dataproperties.getProperty("UPDATE_PRODUCT_ID") + "/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(request, MediaType.APPLICATION_JSON));
        if (response != null && response.getStatus() == Response.Status.OK.getStatusCode()) {
            Reporter.log("Product get call passed with status " + response.getStatus(), true);
        } else {
            logger.info("Product get call failed with status ", response.getStatus());
            Reporter.log("Product get call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
            ProductPricingInformationError error = response.readEntity(ProductPricingInformationError.class);
            logger.info("Product get call failed with status ", error.toString());
            Reporter.log("Product get call failed with status " + error.toString(), true);
        }
        Assert.fail();
    }


    // @Test
    public void postGetProducts() {
        Response response = productServiceClient.path("product/" + dataproperties.getProperty("GET_PRODUCT_ID") + "/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response != null && response.getStatus() == Response.Status.OK.getStatusCode()) {
            Reporter.log("Product get call passed with status " + response.getStatus(), true);
            PricingInformationProduct pricingInformationProduct = response.readEntity(PricingInformationProduct.class);
            Reporter.log(pricingInformationProduct.toString(), true);
            Assert.assertTrue(pricingInformationProduct.getId().equals(dataproperties.getProperty("GET_PRODUCT_ID")));
        } else {
            logger.info("Product get call failed with status ", response.getStatus());
            Reporter.log("Product get call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
            ProductPricingInformationError error = response.readEntity(ProductPricingInformationError.class);
            logger.info("Product get call failed with status ", error.toString());
            Reporter.log("Product get call failed with status " + error.toString(), true);
        }
        Assert.fail();
    }

    // @Test
    public void deleteGetProducts() {
        Response response = productServiceClient.path("product/" + dataproperties.getProperty("GET_PRODUCT_ID") + "/").request(MediaType.APPLICATION_JSON_TYPE).accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response != null && response.getStatus() == Response.Status.OK.getStatusCode()) {
            Reporter.log("Product get call passed with status " + response.getStatus(), true);
            PricingInformationProduct pricingInformationProduct = response.readEntity(PricingInformationProduct.class);
            Reporter.log(pricingInformationProduct.toString(), true);
            Assert.assertTrue(pricingInformationProduct.getId().equals(dataproperties.getProperty("GET_PRODUCT_ID")));
        } else {
            logger.info("Product get call failed with status ", response.getStatus());
            Reporter.log("Product get call failed with status  " + response.getStatus() + " with reason " + response.getStatusInfo(), true);
            ProductPricingInformationError error = response.readEntity(ProductPricingInformationError.class);
            logger.info("Product get call failed with status ", error.toString());
            Reporter.log("Product get call failed with status " + error.toString(), true);
        }
        Assert.fail();
    }

}
