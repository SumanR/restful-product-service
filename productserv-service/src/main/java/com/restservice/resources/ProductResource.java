package com.restservice.resources;

import com.restservice.serviceContracts.productserv.ProductPricingInformation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public interface ProductResource {

    /**
     * API that fetches the product details
     *
     * @param productId
     * @return
     */
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/products/{productId}")
    public Response getProduct(@PathParam("productId") String productId);

    /**
     * Api that helps update the product details i.e price and currency code
     *
     * @param product
     * @return
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/products/{productId}")
    public Response updateProduct(ProductPricingInformation product);

    /**
     * Api that helps create a new product entry in the db
     *
     * @param product
     * @return
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/products/")
    public Response insertProduct(ProductPricingInformation product);

    /**
     * Api that help delete the product entry in the db
     *
     * @param productId
     * @return
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/products/{productId}")
    public Response deleteProduct(@PathParam("productId") String productId);


}