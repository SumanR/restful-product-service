package com.casestudy.restservice.resources;

import com.casestudy.restservice.entities.Product;
import com.casestudy.restservice.exception.ProductServiceException;
import com.casestudy.restservice.product.impl.IProductInformationService;
import com.casestudy.serviceContracts.productserv.ProductPricingInformation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.core.Response;

@Component
public class ProductResourceImpl implements ProductResource {

    private static final Logger logger = LoggerFactory.getLogger(ProductResourceImpl.class);

    @Autowired
    IProductInformationService productInformationService;

    public Response getProduct(String productId) {

        logger.info("Fetching product id " + productId);
        try {
            ProductPricingInformation productInformation = productInformationService.fetchProductInformation(productId);
            logger.info("Returning Successful Response");
            return Response.ok().entity(productInformation).build();

        } catch (ProductServiceException e) {
            logger.info("Returning Failure Response due to " + e.getIssue());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getIssue()).build();

        }


    }

    public Response updateProduct(ProductPricingInformation pricingInformationProduct) {
        logger.info("Updating Product for product id " + pricingInformationProduct.getId());
        try {
            if (!StringUtils.isEmpty(pricingInformationProduct.getId()) && pricingInformationProduct.getCurrency() != null) {
                Product prod = new Product();
                prod.setProductId(pricingInformationProduct.getId());
                prod.setPrice(pricingInformationProduct.getCurrency().getPrice());
                prod.setCurrencyCode(pricingInformationProduct.getCurrency().getCurrentCode());
                productInformationService.updateProductInformation(prod);
                logger.info("Returning Response");
                return Response.ok().build();
            } else {
                logger.info("Returning Failure Response due to request body not having necessary details");
                return Response.status(400).entity("Product Information missing in request").build();
            }
        } catch (ProductServiceException ex) {
            logger.info("Returning Failure Response due to " + ex.getIssue());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getIssue()).build();
        }

    }

    public Response insertProduct(ProductPricingInformation pricingInformationProduct) {
        logger.info("Inserting Product for product id " + pricingInformationProduct.getId());
        try {
            if (!StringUtils.isEmpty(pricingInformationProduct.getId()) && pricingInformationProduct.getCurrency() != null) {
                Product prod = new Product();
                prod.setProductId(pricingInformationProduct.getId());
                prod.setPrice(pricingInformationProduct.getCurrency().getPrice());
                prod.setCurrencyCode(pricingInformationProduct.getCurrency().getCurrentCode());
                productInformationService.insertProductInformation(prod);
                logger.info("Returning Response");
                return Response.ok().build();
            } else {
                return Response.status(400).entity("Product Information missing in request").build();
            }
        } catch (ProductServiceException ex) {
            logger.info("Returning Failure Response due to " + ex.getIssue());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getIssue()).build();
        }
    }

    public Response deleteProduct(String productId) {
        logger.info("Deleting product id " + productId);
        try {
            productInformationService.deleteProductInformation(productId);
            logger.info("Returning Response");
            return Response.ok().build();
        } catch (ProductServiceException ex) {
            logger.info("Returning Failure Response due to  " + ex.getIssue());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getIssue()).build();
        }
    }
}

