package com.tw.shoppify.pricing.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public interface PricingRepo extends JpaRepository<Pricing, String> {
}