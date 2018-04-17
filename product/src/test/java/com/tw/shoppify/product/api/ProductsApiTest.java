package com.tw.shoppify.product.api;

import com.tw.shoppify.product.ApiSupport;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class ProductsApiTest extends ApiSupport {
    @Test
    public void should_201_create_product() {
        myGiven()
                .body(new HashMap() {{
                    put("name", "productName");
                }})

                .when()
                .post("/stores/mystore/products")

                .then()
                .statusCode(201)
                .header("Location", containsString("mystoreproductName"));
    }
}