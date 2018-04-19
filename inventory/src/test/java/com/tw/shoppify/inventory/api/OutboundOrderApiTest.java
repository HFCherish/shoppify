package com.tw.shoppify.inventory.api;

import com.tw.shoppify.inventory.ApiTest;
import com.tw.shoppify.inventory.appservice.Product;
import com.tw.shoppify.inventory.domain.Inventory;
import com.tw.shoppify.inventory.domain.InventoryRepo;
import com.tw.shoppify.inventory.domain.OutboundOrderRepo;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class OutboundOrderApiTest extends ApiTest {
    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    OutboundOrderRepo outboundOrderRepo;

    @Test
    public void should_201_outbound_order_and_409_when_out_of_stock() {
        Product product = new Product("test", "test_store");
        setProductExists(product);
        Inventory oriInventory = new Inventory(product.getId(), 11);
        inventoryRepo.save(oriInventory);

//        201
        String location = postOutboundOrder(product, 10)
                .statusCode(201)
                .header("Location", matchesPattern("^.*/outbound-orders/.*$"))

                .extract()
                .header("Location");

        Optional<Inventory> newInventory = inventoryRepo.findFirstByProductIdOrderByCreateAtDesc(product.getId());
        assertThat(newInventory.isPresent(), is(true));
        assertThat(newInventory.get().getAmount(), is(1));

        String[] split = location.split("/");
        String outboundOrderId = split[split.length - 1];
        outboundOrderRepo.deleteById(outboundOrderId);

//        409
        postOutboundOrder(product, 13)
                .statusCode(409);

        inventoryRepo.delete(oriInventory);
    }

    @Test
    public void should_404_if_product_not_exists_when_create_outbound_order() {
        Product product = new Product("test", "test_store");
        setProductNotExist(product.getId());

        postOutboundOrder(product, 10)
                .statusCode(404);
    }

    private ValidatableResponse postOutboundOrder(Product product, int quantity) {
        return myGiven()
                .body(outboundOrderBody(product, quantity))

                .when()
                .post(outboundOrdersUrl())

                .then();
    }

    private HashMap outboundOrderBody(Product product, final int quantity) {
        return new HashMap() {{
            put("quantity", quantity);
            put("product_id", product.getId());
            put("orderitem_id", "orderitem_id");
        }};
    }

    private String outboundOrdersUrl() {
        return "/outbound-orders";
    }

}