package com.tw.shoppify.inventory.api;

import com.tw.shoppify.inventory.domain.Inventory;
import org.springframework.stereotype.Component;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Component
public class Routes {
    public String inventoryUrl(Inventory inventory) {
        return "/inventories/" + inventory.getId();
    }
}
