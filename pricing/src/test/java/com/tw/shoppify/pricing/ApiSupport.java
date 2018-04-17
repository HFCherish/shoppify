package com.tw.shoppify.pricing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PricingApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public abstract class ApiSupport {


    @BeforeClass
    public static void setUp() {
        RestAssured.port = 8002;
    }

    public RequestSpecification myGiven() {
        return given()
                .contentType(ContentType.JSON);
    }
}
