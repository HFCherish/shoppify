package com.tw.shoppify.pricing.domain;

import com.tw.shoppify.pricing.PricingApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static com.tw.shoppify.pricing.TestSupport.preparePricings_current_10_and_13_5;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PricingApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PricingRepoTest {

    @Autowired
    PricingRepo pricingRepo;

    @Test
    public void should_find_current_pricing_right() throws InterruptedException {
        List<Pricing> savedPricings = preparePricings_current_10_and_13_5(new Product("name1", "store"), new Product("name2", "store"));
        pricingRepo.saveAll(savedPricings);

// without productId
        List<Pricing> latestPricings = pricingRepo.findLatestPricings();
        assertThat(latestPricings.size(), greaterThanOrEqualTo(2));
        assertThat(latestPricings.stream().map(Pricing::getValue).collect(Collectors.toList()), hasItems(13.5d, 10.0d));

// with productId
        List<Pricing> byProduct = pricingRepo.findFirstByProductIdOrderByCreateAtDesc(savedPricings.get(0).getProductId());
        assertThat(byProduct.size(), is(1));
        assertThat(byProduct.get(0).getValue(), is(10.0d));

        pricingRepo.deleteAll(savedPricings);
    }

}