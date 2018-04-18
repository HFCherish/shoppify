package com.tw.shoppify.pricing;

import com.tw.shoppify.pricing.domain.Pricing;
import com.tw.shoppify.pricing.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
public class TestSupport {
    public static List<Pricing> preparePricings_current_10_and_13_5() throws InterruptedException {
        Product product = new Product("name", "store");
        Product product2 = new Product("name", "store");
        List<Pricing> pricings = new ArrayList<>();

        pricings.addAll(asList(
                new Pricing(product.getId(), 13.6),
                new Pricing(product2.getId(), 34.2),
                new Pricing(product2.getId(), 26.3)
        ));
        Thread.sleep(2);

        pricings.addAll(asList(
                new Pricing(product2.getId(), 13.5),
                new Pricing(product.getId(), 10.0)
        ));
        return pricings;
    }
}
