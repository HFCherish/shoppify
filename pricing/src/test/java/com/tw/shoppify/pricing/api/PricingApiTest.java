package com.tw.shoppify.pricing.api;

import com.alibaba.fastjson.JSON;
import com.tw.shoppify.pricing.ApiSupport;
import com.tw.shoppify.pricing.domain.PricingRepo;
import com.tw.shoppify.pricing.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.ForwardChainExpectation;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;

import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class PricingApiTest extends ApiSupport {
    @Autowired
    PricingRepo pricingRepo;

    private static MockServerClient mockServer;
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 8001);

    @Test
    public void should_201_pricing() throws Exception {
        Product product = new Product("test", "test_store");
        whenGetProduct(product.getId())
                .respond(
                        response()
                                .withHeader(new Header(CONTENT_TYPE, MediaType.APPLICATION_JSON))
                                .withStatusCode(200)
                                .withBody(JSON.toJSONString(product))
                );

        String location = myGiven()
                .body(new HashMap() {{
                    put("value", 23.4);
                }})

                .when()
                .post("/products/" + product.getId() + "/pricings")

                .then()
                .statusCode(201)
                .header("Location", matchesPattern("^.*/products/.*/pricings/.*$"))

                .extract()
                .header("Location");

        String[] split = location.split("/");
        String pricingId = split[split.length - 1];

        pricingRepo.deleteById(pricingId);
    }

    private ForwardChainExpectation whenGetProduct(String productId) {
        return mockServer.when(
                request()
                        .withMethod("GET")
                        .withPath("/products/" + productId)
        );
    }

    @Test
    public void should_404_when_pricing_for_product_not_exists() throws Exception {
        whenGetProduct("notExistId")
                .respond(
                        response()
                                .withStatusCode(404)
                );

        myGiven()
                .body(new HashMap() {{
                    put("value", 23.4);
                }})

                .when()
                .post("/products/" + "notExistId" + "/pricings")

                .then()
                .statusCode(404);

    }

}