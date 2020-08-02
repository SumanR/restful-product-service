package com.restservice.serviceContracts.productserv;

import com.fasterxml.jackson.annotation.*;

import javax.xml.bind.annotation.XmlRootElement;

@JsonPropertyOrder({"value", "currency_code"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PricingInformation {

    @JsonProperty("value")
    Double price;


    @JsonProperty("currency_code")
    String currentCode;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(String currentCode) {
        this.currentCode = currentCode;
    }
}
