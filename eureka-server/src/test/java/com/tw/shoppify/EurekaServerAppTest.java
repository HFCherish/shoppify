package com.tw.shoppify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author hf_cherish
 * @date 4/19/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EurekaServerApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class EurekaServerAppTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void should_get_eureka_catalogs() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> forEntity = testRestTemplate.getForEntity("/eureka/apps", Map.class);
        assertThat(forEntity.getStatusCode(), is(HttpStatus.OK));

        System.out.println(forEntity.getBody());
    }
}