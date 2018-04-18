package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.ApiTest;
import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.PricingRepo;
import com.tw.shoppify.pricing.domain.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.tw.shoppify.pricing.TestSupport.preparePricings_current_10_and_13_5;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
public class CurrentPricingApiTest extends ApiTest {
    @Autowired
    PricingRepo pricingRepo;

    @Test
    public void should_get_all_product_current_pricings() throws InterruptedException {
        Product product = new Product("name1", "store");
        Product product1 = new Product("name2", "store");
        List<Pricing> pricings = preparePricings_current_10_and_13_5(product, product1);
        pricingRepo.saveAll(pricings);

//        query all
        myGiven()
                .when()
                .get("/pricings/current")

                .then()
                .statusCode(200)
                .body("product.id.size", greaterThanOrEqualTo(2))
                .body("product", everyItem(not(hasKey("name"))))
                .body("product", everyItem(not(hasKey("store"))))
                .body("price", hasItems(13.5f, 10.0f));

//        by product id & get product detail
        setProductExists(product);
        myGiven()
                .queryParam("product_id", product.getId())
                .queryParam("get_product_detail", true)

                .when()
                .get("/pricings/current")

                .then()
                .statusCode(200)
                .body("product.id.size", is(1))
                .body("product[0].name", is(product.getName()))
                .body("product[0].store", is(product.getStore()))
                .body("price[0]", is(10.0f));

        pricingRepo.deleteAll(pricings);
    }

}