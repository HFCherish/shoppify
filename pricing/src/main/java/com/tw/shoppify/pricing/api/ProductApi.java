package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.appservice.gateway.ProductGateWay;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Path("/products")
public class ProductApi {

    @Autowired
    ProductGateWay productGateWay;

    @Context
    private ResourceContext resourceContext;

    @Path("/{productId}")
    public PricingApi getProduct(@PathParam("productId") String productId) throws Exception {
        return productGateWay.findById(productId)
                .map(resourceContext.getResource(PricingApi.class)::setProduct)
                .orElseThrow(() -> new NotFoundException("product not exists"));
    }
}
