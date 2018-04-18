package com.tw.shoppify.pricing.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE)
public class ProductCurrentPricing implements Serializable {
    private Product product;
    private double price;

    public ProductCurrentPricing(Product product, double price) {
        this.product = product;
        this.price = price;
    }

    public ProductCurrentPricing(Product product, Pricing pricing) {
        this(product, pricing.getValue());
    }
}
