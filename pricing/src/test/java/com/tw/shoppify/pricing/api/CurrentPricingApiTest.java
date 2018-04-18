package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.ApiTest;
import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.PricingRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.tw.shoppify.pricing.TestSupport.preparePricings_current_13_5_and_10;
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
        List<Pricing> pricings = preparePricings_current_13_5_and_10();
        pricingRepo.saveAll(pricings);

        myGiven()
                .when()
                .get("/pricings/current")

                .then()
                .statusCode(200)
                .body("product.id.size", greaterThanOrEqualTo(2));


        pricingRepo.deleteAll(pricings);
    }

}