package com.tw.shoppify.pricing.api;

import com.tw.shoppify.pricing.ApiTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class EchoApiTest extends ApiTest {
    @Test
    public void should_say_hello() {
        myGiven()

                .when()
                .get("/echo")

                .then()
                .statusCode(200)
                .body(containsString("hello"));
    }

}