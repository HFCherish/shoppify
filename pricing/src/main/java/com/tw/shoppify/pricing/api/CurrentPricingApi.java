package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.appservice.PricingService;
import com.tw.shoppify.pricing.domain.ProductCurrentPricing;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
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
    public List<ProductCurrentPricing> currentPricings(
            @QueryParam("product_id") String productId,
            @DefaultValue("false") @QueryParam("get_product_detail") Boolean getProductDetail) {
        return pricingService.findLatestPricings(productId, getProductDetail);
    }
}
