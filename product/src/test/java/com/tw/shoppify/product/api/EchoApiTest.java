package com.tw.shoppify.product.api;

import com.tw.shoppify.product.ApiSupport;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class EchoApiTest extends ApiSupport {
    @Test
    public void should_say_hello() {
        given()
                .contentType(ContentType.JSON)

                .when()
                .get("/echo")

                .then()
                .statusCode(200)
                .body(containsString("hello"));
    }
}