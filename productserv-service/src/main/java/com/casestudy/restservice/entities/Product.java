package com.casestudy.restservice.entities;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = -305726463442998985L;

    String productId;

    Double price;

    String currencyCode;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }



}
