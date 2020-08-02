package com.restservice.serviceContracts.productserv;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

@JsonPropertyOrder({"id","name","currency"})
@JsonRootName(value = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPricingInformation implements Serializable {

    @JsonProperty("id")
    String id;

    @JsonProperty("name")
    String name;

    @JsonProperty("current_price")
    PricingInformation currency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PricingInformation getCurrency() {
        return currency;
    }

    public void setCurrency(PricingInformation currency) {
        this.currency = currency;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
