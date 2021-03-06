package com.tw.shoppify.product.api;

import com.tw.shoppify.product.ApiSupport;
import com.tw.shoppify.product.domain.Product;
import com.tw.shoppify.product.domain.ProductRepo;
import com.tw.timeutils.TimeUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
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

    @Test
    public void should_200_get_products() {
        String store = "store" + TimeUtil.currentTime();
        List<Product> saved = asList(
                new Product("name", store),
                new Product("name1", store),
                new Product("name", "store1"));
        productRepo.saveAll(saved);

        myGiven()
                .when()
                .get("/products")

                .then()
                .statusCode(200)
                .body("id.size", greaterThan(3));


        myGiven()
                .queryParam("store", store)

                .when()
                .get("/products")

                .then()
                .statusCode(200)
                .body("id.size", is(2));

        productRepo.deleteAll(saved);
    }

    @Test
    public void should_get_detail_of_product() {
        Product save = productRepo.save(new Product("name", "store"));

        myGiven()
                .when()
                .get("/products/" + save.getId())

                .then()
                .statusCode(200)
                .body("id", is(save.getId()))
                .body("name", is(save.getName()))
                .body("store", is(save.getStore()));

        productRepo.deleteById(save.getId());
    }

}