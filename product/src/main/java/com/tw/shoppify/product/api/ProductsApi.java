package com.tw.shoppify.product.api;

import com.tw.shoppify.product.domain.Product;
import com.tw.shoppify.product.domain.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Path("/stores/{storeName}/products")
public class ProductsApi {
    @Autowired
    ProductRepo productRepo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("storeName") String storeName,
                           @Context Routes routes,
                           Product product) {
        Product save = productRepo.save(new Product(product.getName(), storeName));
        return Response.created(URI.create(routes.productUrl(save))).build();
    }
}
