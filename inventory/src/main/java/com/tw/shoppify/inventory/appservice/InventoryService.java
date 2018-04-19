package com.tw.shoppify.inventory.appservice;

import com.mysql.jdbc.StringUtils;
import com.tw.shoppify.inventory.appservice.gateway.ProductGateWay;
import com.tw.shoppify.inventory.domain.Inventory;
import com.tw.shoppify.inventory.domain.InventoryRepo;
import com.tw.shoppify.inventory.domain.OutboundOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Service
public class InventoryService {
    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    ProductGateWay productGateWay;

    public Inventory save(Product product, Inventory inventory) {
        return inventoryRepo.save(StringUtils.isNullOrEmpty(inventory.getId()) ?
                new Inventory(product.getId(), inventory.getAmount()) :
                inventory);
    }

    public Inventory findCurrentInventory(Product product) {
        return inventoryRepo.findFirstByProductIdOrderByCreateAtDesc(product.getId()).orElse(Inventory.emptyInventory(product.getId()));
    }

    Inventory outbound(OutboundOrder outboundOrder) {
        String productId = outboundOrder.getProductId();
        Product product = productGateWay.findByIdWithException(productId);
        Inventory currentInventory = findCurrentInventory(product);

        save(product, currentInventory.outbound(outboundOrder.getQuantity()));

        return currentInventory;
    }
}
