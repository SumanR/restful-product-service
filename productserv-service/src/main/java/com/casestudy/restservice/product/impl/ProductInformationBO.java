package com.casestudy.restservice.product.impl;

import com.casestudy.restservice.entities.Product;
import com.casestudy.serviceContracts.productserv.ProductPricing;

public interface ProductInformationBO {

    public ProductPricing fetchProductInformation(String productId);

    public ProductPricing updateProductInformation(Product product);

    public ProductPricing insertProductInformation(Product prod);

    public ProductPricing deleteProductInformation(String productId);
}
