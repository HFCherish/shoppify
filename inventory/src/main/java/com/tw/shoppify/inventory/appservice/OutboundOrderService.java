package com.tw.shoppify.inventory.appservice;

import com.tw.shoppify.inventory.domain.Inventory;
import com.tw.shoppify.inventory.domain.OutboundOrder;
import com.tw.shoppify.inventory.domain.OutboundOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Service
public class OutboundOrderService {
    @Autowired
    OutboundOrderRepo outboundOrderRepo;

    @Autowired
    InventoryService inventoryService;

    public OutboundOrder save(OutboundOrder outboundOrder) {
        Inventory previousInventory = inventoryService.outbound(outboundOrder);
        return outboundOrderRepo.save(outboundOrder.withPreviousInventory(previousInventory));
    }
}
