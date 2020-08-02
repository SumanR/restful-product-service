package com.restservice.product.impl;

import com.restservice.commons.RedSkyProductInfoRestClient;
import com.restservice.entities.Product;
import com.restservice.exception.ProductServiceException;
import com.restservice.service.IProductDAO;
import com.restservice.serviceContracts.productserv.PricingInformation;
import com.restservice.serviceContracts.productserv.ProductPricingInformation;
import com.restservice.serviceContracts.redSky.RedSkyProducts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
public class ProductInformationService implements IProductInformationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInformationService.class);

    @Inject
    RedSkyProductInfoRestClient redSkyProductInfoRestClient;

    @Inject
    IProductDAO service;

    /**
     * This method helps fetch the product details from the redsky url and joins it with the pricing information from the nosql db
     *
     * @param productId
     * @return
     * @throws ProductServiceException
     */
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

    /**
     * This method helps in updating the product pricing information in the db
     *
     * @param product - the product to be fetched
     * @throws ProductServiceException
     */
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

    /**
     * This method helps in inserting a product pricing information row in the db
     *
     * @param prod
     * @throws ProductServiceException
     */
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

    /**
     * This method helps in deleting the product pricing information in the db
     *
     * @param productId
     * @throws ProductServiceException
     */
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
