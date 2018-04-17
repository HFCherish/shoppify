package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.ApiSupport;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.text.MatchesPattern.matchesPattern;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class PricingApiTest extends ApiSupport {
    @Test
    public void should_201_pricing() {
        myGiven()
                .body(new HashMap() {{
                    put("value", 23.4);
                }})

                .when()
                .post("/products/productId/pricings")

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^.*productId23\\.4.*$"));
    }
}