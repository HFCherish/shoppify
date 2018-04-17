package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.appservice.PricingService;
import com.tw.shoppify.pricing.domain.Pricing;
import org.springframework.beans.factory.annotation.Autowired;

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
@Path("/products/{productId}/pricings")
public class PricingApi {
    @Autowired
    PricingService pricingService;

    @Autowired
    Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pricing(
            @PathParam("productId") String productId,
            Pricing pricing) throws Exception {


        Pricing save = pricingService.save(productId, pricing);

        return Response.created(URI.create(routes.pricingUrl(save))).build();
    }

}
