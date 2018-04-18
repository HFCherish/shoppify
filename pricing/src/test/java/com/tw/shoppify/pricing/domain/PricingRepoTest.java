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

import static com.tw.shoppify.pricing.TestSupport.preparePricings_current_13_5_and_10;
import static org.hamcrest.CoreMatchers.hasItems;
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
        List<Pricing> savedPricings = preparePricings_current_13_5_and_10();
        pricingRepo.saveAll(savedPricings);


        List<Pricing> latestPricings = pricingRepo.findLatestPricings();


        assertThat(latestPricings.size(), greaterThanOrEqualTo(2));
        assertThat(latestPricings.stream().map(Pricing::getValue).collect(Collectors.toList()), hasItems(13.5, 10.0));

        pricingRepo.deleteAll(savedPricings);
    }

}