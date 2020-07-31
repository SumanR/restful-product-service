package com.casestudy.restservice.resources;

import com.casestudy.serviceContracts.productserv.PricingInformationProduct;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public interface ProductResource {


    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/product/{productId}")
    public Response checkReportExist( @NotEmpty(message = "must not be empty")  @PathParam("productId") String productId);


    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/product/{productId}")
    public Response updateRecord(PricingInformationProduct product);

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/product/")
    public Response insertRecord(PricingInformationProduct product);

    @DELETE
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/product/{productId}")
    public Response deleteRecord(@NotEmpty(message = "must not be empty")  @PathParam("productId") String productId);


}