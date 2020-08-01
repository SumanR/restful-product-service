package com.casestudy.restservice.service;

import com.casestudy.restservice.entities.Product;

public interface IProductDAO {

    Product findById(final String productID);

    void save(final Product product, String productId);

    void update(final Product product, String productID);

    void delete(final String productID);
}
