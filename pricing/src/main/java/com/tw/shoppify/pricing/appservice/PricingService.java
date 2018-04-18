package com.tw.shoppify.pricing.appservice;

import com.mysql.jdbc.StringUtils;
import com.tw.shoppify.pricing.appservice.gateway.ProductGateWay;
import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.PricingRepo;
import com.tw.shoppify.pricing.domain.Product;
import com.tw.shoppify.pricing.domain.ProductCurrentPricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Pricing save(Product product, Pricing pricing) {
        return pricingRepo.save(new Pricing(product.getId(), pricing.getValue()));
    }

    public List<Pricing> findAll(Product product) {
        return pricingRepo.findByProductId(product.getId());
    }

    public List<ProductCurrentPricing> findLatestPricings(String productId, Boolean getProductDetail) {
        List<Pricing> pricings = StringUtils.isNullOrEmpty(productId) ? pricingRepo.findLatestPricings() : pricingRepo.findFirstByProductIdOrderByCreateAtDesc(productId);
        return pricings.stream()
                .map(p -> new ProductCurrentPricing(getProductDetail ? productGateWay.findById(p.getProductId()).get() : new Product(p.getProductId()), p))
                .collect(Collectors.toList());
    }
}
