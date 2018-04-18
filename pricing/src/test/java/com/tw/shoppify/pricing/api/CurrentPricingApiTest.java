package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.ApiTest;
import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.PricingRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.tw.shoppify.pricing.TestSupport.preparePricings_current_10_and_13_5;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
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
        List<Pricing> pricings = preparePricings_current_10_and_13_5();
        pricingRepo.saveAll(pricings);

        myGiven()

                .when()
                .get("/pricings/current")

                .then()
                .statusCode(200)
                .body("product.id.size", greaterThanOrEqualTo(2))
                .body("price", hasItems(13.5f, 10.0f));

        myGiven()
                .queryParam("product_id", pricings.get(0).getProductId())

                .when()
                .get("/pricings/current")

                .then()
                .statusCode(200)
                .body("product.id.size", is(1))
                .body("price[0]", is(10.0f));


        pricingRepo.deleteAll(pricings);
    }

}