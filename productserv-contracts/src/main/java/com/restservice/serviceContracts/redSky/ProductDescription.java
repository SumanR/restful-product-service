package com.restservice.serviceContracts.redSky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDescription {

    @JsonProperty("title")
    String title;

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
