package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.domain.Pricing;
import org.springframework.stereotype.Component;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Component
public class Routes {
    public String pricingUrl(Pricing pricing) {
        return "/products/" + pricing.getProductId() + "/pricings/" + pricing.getId();
    }
}
