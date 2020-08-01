package com.casestudy.restservice.product.impl;

import com.casestudy.restservice.entities.Product;
import com.casestudy.restservice.exception.ProductServiceException;
import com.casestudy.serviceContracts.productserv.ProductPricingInformation;

public interface IProductInformationService {

    ProductPricingInformation fetchProductInformation(String productId) throws ProductServiceException;

    void updateProductInformation(Product product) throws ProductServiceException;

    void insertProductInformation(Product prod) throws ProductServiceException;

    void deleteProductInformation(String productId) throws ProductServiceException;
}
