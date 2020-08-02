package com.restservice.product.impl;

import com.restservice.entities.Product;
import com.restservice.exception.ProductServiceException;
import com.restservice.serviceContracts.productserv.ProductPricingInformation;

public interface IProductInformationService {

    ProductPricingInformation fetchProductInformation(String productId) throws ProductServiceException;

    void updateProductInformation(Product product) throws ProductServiceException;

    void insertProductInformation(Product prod) throws ProductServiceException;

    void deleteProductInformation(String productId) throws ProductServiceException;
}
