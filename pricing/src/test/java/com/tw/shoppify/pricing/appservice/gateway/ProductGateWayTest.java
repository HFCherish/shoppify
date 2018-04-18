package com.tw.shoppify.pricing.appservice.gateway;

import com.tw.shoppify.pricing.domain.Product;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Ignore
public class ProductGateWayTest {

    private ProductGateWay productGateWay;

    @Before
    public void setUp() {
        productGateWay = new ProductGateWay();
    }

    @Test
    public void should_empty_when_find_not_exists() {
        Optional<Product> notExist = productGateWay.findById("notExist");
        assertThat(notExist.isPresent(), is(false));
    }

    @Test
    public void should_find_right_when_exists() {
        Optional<Product> byId = productGateWay.findById("111");
        assertThat(byId.isPresent(), is(true));
        assertThat(byId.get().getId(), is("111"));
        assertThat(byId.get().getName(), is("product_name_for_test_1"));
        assertThat(byId.get().getStore(), is("store_name_for_test_1"));
    }

}