package com.restservice;

import com.restservice.entities.Product;
import com.restservice.service.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class RestServiceApplicationTests {

    @Autowired
    ProductDAO service;

    @BeforeEach
    public void loadData() {

        insertProduct("13860428", "USD", 123.4);
        insertProduct("54456119", "AUD", 125.4);
        insertProduct("13264003", "CAD", 223.9);
        insertProduct("12954218", "EUR", 1023.4);

    }

    @Test
    void contextLoads() {
    }

    @Test
    void testRedisAccess() {
        ProductDAO productDAO;
        String productID;
        final Product product = service.findById("13860428");
        System.out.println("Product" + product);

        product.setCurrencyCode("AUD");
        service.update(product, "13860428");

        Product product1 = service.findById("13860428");
        System.out.println("Product" + product1);

        Product product2 = service.findById("54456119");
        System.out.println("Product" + product2);

        List<Product> products = service.findByPattern("product*");
        products.forEach(System.out::println);
        Assert.notNull(products, "the list is empty");

    }

    private void insertProduct(String productID, String currencyCode, Double price) {

        Product pro = new Product();
        pro.setCurrencyCode(currencyCode);
        pro.setPrice(price);
        pro.setProductId(productID);

        service.save(pro, pro.getProductId());

    }

}
