package com.casestudy.serviceContracts.productserv;


import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

//{"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
@JsonPropertyOrder({ "products"})
@JsonRootName(value = "products")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PricingInformationProducts {

    @JsonProperty("products")
    private List<PricingInformationProduct> products = new ArrayList<PricingInformationProduct>();

    public List<PricingInformationProduct> getProducts() {
        return products;
    }

    public void setProducts(List<PricingInformationProduct> products) {
        this.products = products;
    }



}
