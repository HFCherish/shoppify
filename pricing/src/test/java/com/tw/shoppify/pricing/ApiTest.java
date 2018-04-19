package com.tw.shoppify.pricing;

import com.alibaba.fastjson.JSON;
import com.tw.shoppify.pricing.domain.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockserver.client.server.ForwardChainExpectation;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PricingApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public abstract class ApiTest {


    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 8001);
    protected MockServerClient mockServer;

    @BeforeClass
    public static void setUp() {
        RestAssured.port = 8002;
    }

    public RequestSpecification myGiven() {
        return given()
                .contentType(ContentType.JSON);
    }

    protected void setProductExists(Product product) {
        whenGetProduct(product.getId())
                .respond(
                        response()
                                .withHeader(new Header(CONTENT_TYPE, MediaType.APPLICATION_JSON))
                                .withStatusCode(200)
                                .withBody(JSON.toJSONString(product))
                );
    }

    protected ForwardChainExpectation whenGetProduct(String productId) {
        return mockServer.when(
                request()
                        .withMethod("GET")
                        .withPath("/products/" + productId)
        );
    }

    protected void setProductNotExist(String productId) {
        whenGetProduct(productId)
                .respond(
                        response()
                                .withStatusCode(404)
                );
    }
}
