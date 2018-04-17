package com.tw.shoppify.product;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApiSupport {

    @BeforeClass
    public static void setUp() {
        RestAssured.port = 8001;
    }
}
