package com.tw.shoppify.inventory.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tw.stringutils.IdGenerator;
import com.tw.timeutils.TimeUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "inventories")
public class Inventory {
    @Id
    private String id;
    @Column(name = "product_id")
    @JsonProperty("product_id")
    private String productId;
    private int amount;
    @Column(name = "create_at")
    @JsonProperty("create_at")
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

    public static Inventory emptyInventory(String productId) {
        Inventory inventory = new Inventory();
        inventory.productId = productId;
        inventory.amount = 0;
        inventory.createAt = TimeUtil.currentTime();
        return inventory;
    }

    private Inventory() {
    }

    public int getAmount() {
        return amount;
    }
}
