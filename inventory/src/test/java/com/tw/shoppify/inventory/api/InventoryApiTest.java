package com.tw.shoppify.inventory.api;

import com.tw.shoppify.inventory.ApiTest;
import com.tw.shoppify.inventory.appservice.Product;
import com.tw.shoppify.inventory.domain.InventoryRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class InventoryApiTest extends ApiTest {
    @Autowired
    InventoryRepo inventoryRepo;

    @Test
    public void should_201_pricing() {
        Product product = new Product("test", "test_store");
        setProductExists(product);

        String location = myGiven()
                .body(new HashMap() {{
                    put("amount", 10);
                }})

                .when()
                .post(inventoriesUrl(product.getId()))

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^.*/inventories/.*$"))

                .extract()
                .header("Location");

        String[] split = location.split("/");
        String pricingId = split[split.length - 1];

        inventoryRepo.deleteById(pricingId);
    }

    @Test
    public void should_404_when_pricing_for_product_not_exists() {
        whenGetProduct("notExistId")
                .respond(
                        response()
                                .withStatusCode(404)
                );

        myGiven()
                .body(new HashMap() {{
                    put("amount", 10);
                }})

                .when()
                .post(inventoriesUrl("notExistId"))

                .then()
                .statusCode(404);

    }

    private String inventoriesUrl(String productId) {
        return "/products/" + productId + "/inventories";
    }

//    @Test
//    public void should_200_get_all() {
//        Product product = new Product("name", "store");
//        setProductExists(product);
//        List<Inventory> savedPricings = asList(
//                new Inventory(product.getId(), 34.2),
//                new Inventory(product.getId(), 26.3),
//                new Inventory(product.getId(), 13.5)
//        );
//        inventoryRepo.saveAll(savedPricings);
//
//        myGiven()
//                .when()
//                .get(inventoriesUrl(product.getId()))
//
//                .then()
//                .statusCode(200)
//                .body("value.size", is(3));
//
//        inventoryRepo.deleteAll(savedPricings);
//    }
}