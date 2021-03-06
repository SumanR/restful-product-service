package com.restservice.serviceContracts.redSky;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.ToStringBuilder;


@JsonRootName(value = "")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedSkyProducts {   //RedSkyProductResponse

    @JsonProperty("product")
    Product product;

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
