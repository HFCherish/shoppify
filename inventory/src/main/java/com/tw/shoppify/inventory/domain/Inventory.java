package com.tw.shoppify.inventory.domain;

import com.tw.stringutils.IdGenerator;
import com.tw.timeutils.TimeUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    private String id;
    @Column(name = "product_id")
    private String productId;
    private int amount;
    @Column(name = "create_at")
    private Long createAt;

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public Inventory(String productId, Integer amount) {
        this.productId = productId;
        this.amount = amount;
        this.id = IdGenerator.next();
        this.createAt = TimeUtil.currentTime();
    }

    private Inventory() {
    }

    public int getAmount() {
        return amount;
    }
}
