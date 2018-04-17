package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.ApiSupport;
import com.tw.shoppify.pricing.domain.PricingRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.hamcrest.text.MatchesPattern.matchesPattern;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class PricingApiTest extends ApiSupport {
    @Autowired
    PricingRepo pricingRepo;

    @Test
    public void should_201_pricing() {
        String location = myGiven()
                .body(new HashMap() {{
                    put("value", 23.4);
                }})

                .when()
                .post("/products/productId/pricings")

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^.*/products/productId/pricings/.*$"))

                .extract()
                .header("Location");

        String[] split = location.split("/");
        String pricingId = split[split.length - 1];

        pricingRepo.deleteById(pricingId);
    }
}