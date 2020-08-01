package com.casestudy.restservice.product.impl;

import com.casestudy.restservice.entities.Product;
import com.casestudy.restservice.exception.ProductServiceException;
import com.casestudy.serviceContracts.productserv.ProductPricingInformation;

public interface ProductInformationBO { //TODO :Rename to product service

    public ProductPricingInformation fetchProductInformation(String productId) throws ProductServiceException;

    public void updateProductInformation(Product product) throws ProductServiceException;

    public void insertProductInformation(Product prod) throws ProductServiceException;

    public void deleteProductInformation(String productId) throws ProductServiceException;
}
