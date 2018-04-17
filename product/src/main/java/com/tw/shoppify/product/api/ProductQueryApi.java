package com.tw.shoppify.product.api;

import com.tw.shoppify.product.domain.Product;
import com.tw.shoppify.product.domain.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static com.tw.shoppify.product.infrastructure.dto.ProductExampleFactory.productOfStoreExample;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Path("/products")
public class ProductQueryApi {
    @Autowired
    ProductRepo productRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll(@QueryParam("store") String storeName) {
        return productRepo.findAll(productOfStoreExample(storeName));
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getOne(@PathParam("productId") String productId) {
        return productRepo.findById(productId).orElseThrow(() -> new NotFoundException("product " + productId + " not exists"));
    }
}
