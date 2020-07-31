package com.casestudy.restservice.product.impl;

import com.casestudy.restservice.commons.RedSkyProductInfoRestClient;
import com.casestudy.restservice.entities.Product;
import com.casestudy.restservice.resources.ProductResourceImpl;
import com.casestudy.restservice.service.ProductService;
import com.casestudy.serviceContracts.productserv.PricingInformation;
import com.casestudy.serviceContracts.productserv.PricingInformationProduct;
import com.casestudy.serviceContracts.productserv.ProductPricing;
import com.casestudy.serviceContracts.productserv.ProductPricingInformationError;
import com.casestudy.serviceContracts.redSky.RedSkyProducts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
public class ProductInformationBOImpl implements ProductInformationBO {

    @Inject
    RedSkyProductInfoRestClient redSkyProductInfoRestClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResourceImpl.class);

    @Inject
    ProductService service;

    public ProductPricing fetchProductInformation(String productId) {

        //TODO: Parallelize db fetch and client response and stich
        try {
            RedSkyProducts redSkyProducts = redSkyProductInfoRestClient.queryRedSky(productId);
            if (redSkyProducts != null) {
                String title = redSkyProducts.getProduct().getItem().getProductDescription().getTitle();
                //TODO : use builder and formate
                PricingInformationProduct pricingProduct = new PricingInformationProduct();
                pricingProduct.setId(productId);
                pricingProduct.setName(title);

                //fetch product price from DB
                Product product = service.findById(productId);
                if(product!=null) {
                    PricingInformation info = new PricingInformation();
                    info.setCurrentCode(product.getCurrencyCode());
                    info.setPrice(product.getPrice());
                    pricingProduct.setCurrency(info);
                }
                return pricingProduct;
            } else {
                ProductPricingInformationError productPricingInformationError = new ProductPricingInformationError();
                productPricingInformationError.setIssue("Unable to Fetch product name");
                return productPricingInformationError;
            }
        }catch (Exception e) {
            ProductPricingInformationError productPricingInformationError = new ProductPricingInformationError();
            productPricingInformationError.setIssue(e.getLocalizedMessage());
            return productPricingInformationError;
        }

    }

    @Override
    public ProductPricing updateProductInformation(Product product) {
        try {
            service.update(product, product.getProductId());
        } catch (Exception e) {
            ProductPricingInformationError productPricingInformationError = new ProductPricingInformationError();
            productPricingInformationError.setIssue(e.getLocalizedMessage());
            return productPricingInformationError;
        }
        return null;
    }

    @Override
    public ProductPricing insertProductInformation(Product prod) {
        try {
            service.save(prod, prod.getProductId());
        } catch (Exception e) {
            ProductPricingInformationError productPricingInformationError = new ProductPricingInformationError();
            productPricingInformationError.setIssue(e.getLocalizedMessage());
            return productPricingInformationError;
        }
        return null;
    }

    @Override
    public ProductPricing deleteProductInformation(String productId) {
        try {
            service.delete(productId);
        } catch (Exception e) {
            ProductPricingInformationError productPricingInformationError = new ProductPricingInformationError();
            productPricingInformationError.setIssue(e.getLocalizedMessage());
            return productPricingInformationError;
        }
        return null;
    }



}
