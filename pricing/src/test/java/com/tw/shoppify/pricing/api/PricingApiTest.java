package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.ApiTest;
import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.PricingRepo;
import com.tw.shoppify.pricing.domain.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class PricingApiTest extends ApiTest {
    @Autowired
    PricingRepo pricingRepo;

    @Test
    public void should_201_pricing() {
        Product product = new Product("test", "test_store");
        setProductExists(product);

        String location = myGiven()
                .body(new HashMap() {{
                    put("value", 23.4);
                }})

                .when()
                .post(pricingsUrl(product.getId()))

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^.*/products/.*/pricings/.*$"))

                .extract()
                .header("Location");

        String[] split = location.split("/");
        String pricingId = split[split.length - 1];

        pricingRepo.deleteById(pricingId);
    }

    @Test
    public void should_404_when_pricing_for_product_not_exists() throws Exception {
        setProductNotExist("notExistId");

        myGiven()
                .body(new HashMap() {{
                    put("value", 23.4);
                }})

                .when()
                .post(pricingsUrl("notExistId"))

                .then()
                .statusCode(404);

    }

    private String pricingsUrl(String notExistId) {
        return "/products/" + notExistId + "/pricings";
    }

    @Test
    public void should_200_get_all() {
        Product product = new Product("name", "store");
        setProductExists(product);
        List<Pricing> savedPricings = asList(
                new Pricing(product.getId(), 34.2),
                new Pricing(product.getId(), 26.3),
                new Pricing(product.getId(), 13.5)
        );
        pricingRepo.saveAll(savedPricings);

        myGiven()
                .when()
                .get(pricingsUrl(product.getId()))

                .then()
                .statusCode(200)
                .body("value.size", is(3));

        pricingRepo.deleteAll(savedPricings);
    }
}