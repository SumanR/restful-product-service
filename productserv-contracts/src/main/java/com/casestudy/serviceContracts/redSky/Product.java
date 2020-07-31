package com.casestudy.serviceContracts.redSky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonProperty("item")
    Item item;

    public Item getItem() {
        return item;
    }

    public void setItems(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
