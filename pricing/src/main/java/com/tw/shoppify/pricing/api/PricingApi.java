package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.appservice.PricingService;
import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class PricingApi {
    @Autowired
    PricingService pricingService;

    @Autowired
    Routes routes;

    private Product product;

    public PricingApi setProduct(Product product) {
        this.product = product;
        return this;
    }

    @Path("pricings")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pricing(Pricing pricing) {
        Pricing save = pricingService.save(product, pricing);

        return Response.created(URI.create(routes.pricingUrl(save))).build();
    }

    @Path("pricings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pricing> getAll() {
        return pricingService.findAll(product);
    }


}
