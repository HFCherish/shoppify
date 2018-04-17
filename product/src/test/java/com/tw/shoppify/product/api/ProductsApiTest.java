package com.tw.shoppify.product.api;

import com.tw.shoppify.product.ApiSupport;
import com.tw.shoppify.product.domain.ProductRepo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.hamcrest.text.MatchesPattern.matchesPattern;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class ProductsApiTest extends ApiSupport {
    @Autowired
    ProductRepo productRepo;

    @Test
    public void should_201_create_product() {
        String location = myGiven()
                .body(new HashMap() {{
                    put("name", "productName");
                }})

                .when()
                .post("/stores/mystore/products")

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^.*/stores/mystore/products/.*$"))

                .extract()
                .header("Location");

        String[] split = location.split("\\/");
        String productId = split[split.length - 1];

        productRepo.deleteById(productId);
    }
}