package com.casestudy.restservice.interfaces;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
interface ProductResource {


    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/product/{productId}")
    Response checkReportExist(@NotEmpty(message = "must not be empty") @PathParam("productId") String productId);

}