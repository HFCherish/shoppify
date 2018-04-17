package com.tw.shoppify.pricing.domain;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public final class Pricing {
    private String id;
    private String productId;
    private Double value;
    private Long createAt;

    public Double getValue() {
        return value;
    }
}
