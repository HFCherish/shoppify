package com.tw.shoppify.pricing.domain;

import com.tw.shoppify.pricing.domain.util.TimeUtil;
import com.tw.stringutils.IdGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Entity
@Table(name = "pricings")
public class Pricing {
    @Id
    private String id;
    @Column(name = "product_id")
    private String productId;
    private Double value;
    @Column(name = "create_at")
    private Long createAt;

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public Pricing(String productId, Double value) {
        this.productId = productId;
        this.value = value;
        this.id = IdGenerator.next();
        this.createAt = TimeUtil.currentTime();
    }

    private Pricing() {
    }

    public Double getValue() {
        return value;
    }
}
