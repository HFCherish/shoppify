package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.appservice.PricingService;
import com.tw.shoppify.pricing.domain.ProductCurrentPricing;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
@Path("/pricings/current")
public class CurrentPricingApi {
    @Autowired
    PricingService pricingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductCurrentPricing> currentPricings(@QueryParam("product_id") String productId) {
        return pricingService.findLatestPricings(productId);
    }
}
