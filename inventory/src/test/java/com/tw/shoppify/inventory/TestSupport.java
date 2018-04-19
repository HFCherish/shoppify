package com.tw.shoppify.inventory;

import com.tw.shoppify.inventory.appservice.Product;
import com.tw.shoppify.inventory.domain.Inventory;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
public class TestSupport {
    public static List<Inventory> prepareInventories_13_12_11(Product products) throws InterruptedException {
        List<Inventory> inventories = new ArrayList<>();

        inventories.addAll(asList(
                new Inventory(products.getId(), 13),
                new Inventory(products.getId(), 12)
        ));
        Thread.sleep(2);

        inventories.add(new Inventory(products.getId(), 11));
        return inventories;
    }
}
