package com.casestudy.restservice.resources;

import com.casestudy.restservice.entities.Product;
import com.casestudy.serviceContracts.productserv.PricingInformation;
import com.casestudy.serviceContracts.productserv.PricingInformationProduct;
import com.casestudy.serviceContracts.productserv.ProductPricing;
import com.casestudy.serviceContracts.productserv.ProductPricingInformationError;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.casestudy.restservice.product.impl.ProductInformationBO;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.core.Response;

@Component
public class ProductResourceImpl implements ProductResource{

    private static final   Logger logger = LoggerFactory.getLogger(ProductResourceImpl.class);

    @Autowired
    ProductInformationBO productInformationBo;

    public Response checkReportExist( String productId) {

        logger.info("Fetching product id " + productId);
        ProductPricing productInformation= productInformationBo.fetchProductInformation(productId);
        logger.info("Returning Response");
        if(productInformation instanceof PricingInformationProduct) {
        return Response.status(200).entity(productInformation).build();
        } else {
            return Response.status(500).entity(productInformation).build();
        }

    }

    @Override
    public Response updateRecord(PricingInformationProduct pricingInformationProduct) {
        logger.info("Inserting Product for product id " + pricingInformationProduct.getId());
        if(pricingInformationProduct!=null && !StringUtils.isEmpty(pricingInformationProduct.getId()) && pricingInformationProduct.getCurrency()!=null) {
            Product prod = new Product();
            prod.setProductId(pricingInformationProduct.getId());
            prod.setPrice(pricingInformationProduct.getCurrency().getPrice());
            prod.setCurrencyCode(pricingInformationProduct.getCurrency().getCurrentCode());
            ProductPricing pricing= productInformationBo.updateProductInformation(prod);
            logger.info("Returning Response");
            if(pricing instanceof ProductPricingInformationError) {
                return Response.status(500).build();
            }
            return Response.status(200).build();
        } else {
            return Response.status(400).entity("Product Information missing in request").build();
        }

    }

    @Override
    public Response insertRecord(PricingInformationProduct pricingInformationProduct) {
        logger.info("Inserting Product for product id " + pricingInformationProduct.getId());
        if(pricingInformationProduct!=null && !StringUtils.isEmpty(pricingInformationProduct.getId()) && pricingInformationProduct.getCurrency()!=null) {
            Product prod = new Product();
            prod.setProductId(pricingInformationProduct.getId());
            prod.setPrice(pricingInformationProduct.getCurrency().getPrice());
            prod.setCurrencyCode(pricingInformationProduct.getCurrency().getCurrentCode());
            ProductPricing pricing= productInformationBo.insertProductInformation(prod);
            logger.info("Returning Response");
            if(pricing instanceof ProductPricingInformationError) {
                return Response.status(500).build();
            }
            return Response.status(200).build();
        } else {
            return Response.status(400).entity("Product Information missing in request").build();
        }
    }

    @Override
    public Response deleteRecord(@NotEmpty(message = "must not be empty") String productId) {
        logger.info("Deleting product id " + productId);
        ProductPricing productInformation = productInformationBo.deleteProductInformation(productId);
        logger.info("Returning Response");
        if (productInformation instanceof ProductPricingInformationError) {
            return Response.status(500).entity(productInformation).build();
        } else {
            return Response.status(200).entity(productInformation).build();
        }
    }
}

