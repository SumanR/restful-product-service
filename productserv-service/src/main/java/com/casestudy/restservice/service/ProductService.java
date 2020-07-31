package com.casestudy.restservice.service;


import com.casestudy.restservice.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

import java.util.List;
//import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private RedisTemplate<String, Product> productTemplate;

    private static final String REDIS_PREFIX_USERS = "product";

    private static final String REDIS_KEYS_SEPARATOR = ":";

    public List<Product> findByPattern(final String pattern) {
        return getValueOperations().multiGet(productTemplate.keys(getRedisKey(pattern)));
    }

    public Product findById(final String productID) {
        final Product product = getValueOperations().get(getRedisKey(productID));
        if(product == null) {
            throw new NotFoundException("Product does not exist in the DB");
        }
        return product;
    }

    public void save(final Product product, String productId) {
        //product.setProductId(productId);
        getValueOperations().set(getRedisKey(productId), product);
    }

    public void update(final Product product, String productID) {
        findById(productID);
        getValueOperations().set(getRedisKey(productID), product);
    }

    public void delete(final String productID) {
        if(!productTemplate.delete(getRedisKey(productID))) {
            throw new NotFoundException("User does not exist in the DB");
        }
    }

    private String getRedisKey(final String productID) {
        return REDIS_PREFIX_USERS + REDIS_KEYS_SEPARATOR + productID;
    }

    private ValueOperations<String, Product> getValueOperations() {
        return productTemplate.opsForValue();
    }

}
