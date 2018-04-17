package com.tw.shoppify.product.api;

import com.tw.shoppify.product.domain.Product;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Path("/stores/{storeName}/products")
public class ProductsApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("storeName") String storeName,
                           Product product) {
        return Response.created(URI.create(storeName + product.getName())).build();
    }
}
