package com.tw.shoppify.inventory.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tw.stringutils.IdGenerator;
import com.tw.timeutils.TimeUtil;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "outbound_orders")
public class OutboundOrder {
    @Id
    private String id;

    @Column(name = "product_id")
    @JsonProperty("product_id")
    private String productId;

    @Column(name = "orderitem_id")
    @JsonProperty("orderitem_id")
    private String orderitemId;

    private int quantity;

    @Column(name = "create_at")
    @JsonProperty("create_at")
    private Long createAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_inventory_id")
    private Inventory previousInventory;

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public OutboundOrder(String productId, Integer quantity, String orderitemId, Inventory previousInventory) {
        this.productId = productId;
        this.quantity = quantity;
        this.orderitemId = orderitemId;
        this.previousInventory = previousInventory;
        this.id = IdGenerator.next();
        this.createAt = TimeUtil.currentTime();
    }

    private OutboundOrder() {
    }

    public int getQuantity() {
        return quantity;
    }

    public OutboundOrder withPreviousInventory(Inventory previousInventory) {
        return new OutboundOrder(productId, quantity, orderitemId, previousInventory);
    }
}
