package com.tw.shoppify.pricing.appservice;

import com.tw.shoppify.pricing.appservice.gateway.ProductGateWay;
import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.PricingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Service
public class PricingService {
    @Autowired
    PricingRepo pricingRepo;

    @Autowired
    ProductGateWay productGateWay;

    // TODO: 4/17/18 process exception
    public Pricing save(String productId, Pricing pricing) throws Exception {
        productGateWay.findById(productId).orElseThrow(() -> new NotFoundException("product not exists"));
        return pricingRepo.save(new Pricing(productId, pricing.getValue()));
    }
}
