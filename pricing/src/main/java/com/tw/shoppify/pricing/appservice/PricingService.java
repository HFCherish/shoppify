package com.tw.shoppify.pricing.appservice;

import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.PricingRepo;
import com.tw.shoppify.pricing.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Service
public class PricingService {
    @Autowired
    PricingRepo pricingRepo;

    public Pricing save(Product product, Pricing pricing) {
        return pricingRepo.save(new Pricing(product.getId(), pricing.getValue()));
    }
}
