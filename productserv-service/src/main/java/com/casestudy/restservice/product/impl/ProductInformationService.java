package com.casestudy.restservice.product.impl;

import com.casestudy.restservice.commons.RedSkyProductInfoRestClient;
import com.casestudy.restservice.entities.Product;
import com.casestudy.restservice.exception.ProductServiceException;
import com.casestudy.restservice.resources.ProductResourceImpl;
import com.casestudy.restservice.service.ProductService;
import com.casestudy.serviceContracts.productserv.PricingInformation;
import com.casestudy.serviceContracts.productserv.ProductPricingInformation;
import com.casestudy.serviceContracts.redSky.RedSkyProducts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
public class IProductInformationServiceImpl implements IProductInformationService {

    @Inject
    RedSkyProductInfoRestClient redSkyProductInfoRestClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResourceImpl.class);

    @Inject
    ProductService service;

    public ProductPricingInformation fetchProductInformation(String productId) throws ProductServiceException {

        //TODO: Parallelize db fetch and client response and stitch
        try {
            RedSkyProducts redSkyProducts = redSkyProductInfoRestClient.queryRedSky(productId);
            if (redSkyProducts != null) {
                String title = redSkyProducts.getProduct().getItem().getProductDescription().getTitle();
                //TODO : use builder and format
                ProductPricingInformation pricingProduct = new ProductPricingInformation();
                pricingProduct.setId(productId);
                pricingProduct.setName(title);

                //fetch product price from DB
                Product product = service.findById(productId);
                if (product != null) {
                    PricingInformation info = new PricingInformation();
                    info.setCurrentCode(product.getCurrencyCode());
                    info.setPrice(product.getPrice());
                    pricingProduct.setCurrency(info);
                }
                return pricingProduct;
            } else {

                ProductServiceException pe = new ProductServiceException();
                pe.setIssue("Unable to Fetch product name");
                throw pe;
            }
        } catch (Exception e) {
            ProductServiceException pe = new ProductServiceException();
            pe.setIssue(e.getMessage());
            throw pe;
        }

    }

    @Override
    public void updateProductInformation(Product product) throws ProductServiceException {
        try {
            service.update(product, product.getProductId());
        } catch (Exception e) {
            ProductServiceException pe = new ProductServiceException();
            pe.setIssue(e.getLocalizedMessage());
            throw pe;
        }
    }

    @Override
    public void insertProductInformation(Product prod) throws ProductServiceException {
        try {
            service.save(prod, prod.getProductId());
        } catch (Exception e) {
            ProductServiceException pe = new ProductServiceException();
            pe.setIssue(e.getLocalizedMessage());
            throw pe;
        }
    }

    @Override
    public void deleteProductInformation(String productId) throws ProductServiceException {
        try {
            service.delete(productId);
        } catch (Exception e) {
            ProductServiceException pe = new ProductServiceException();
            pe.setIssue(e.getLocalizedMessage());
            throw pe;
        }
    }


}
