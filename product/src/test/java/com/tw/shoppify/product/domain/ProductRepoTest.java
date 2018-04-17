package com.tw.shoppify.product.domain;

import com.tw.shoppify.product.ProductApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.tw.shoppify.product.infrastructure.dto.ProductExampleFactory.productOfStoreExample;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class ProductRepoTest {
    @Autowired
    ProductRepo productRepo;

    // TODO: 4/17/18 use @SqlGroup to prepare and clear
    @Test
    public void should_query_by_store_right() {
        List<Product> saved = asList(
                new Product("name", "store"),
                new Product("name1", "store"),
                new Product("name", "store1"));
        productRepo.saveAll(saved);

        List<Product> storeProds = productRepo.findAll(productOfStoreExample("store"));
        assertThat(storeProds.size(), is(2));

        List<Product> store1Prods = productRepo.findAll(productOfStoreExample("store1"));
        assertThat(store1Prods.size(), is(1));

        List<Product> allProds = productRepo.findAll(productOfStoreExample(null));
        assertThat(allProds.size() >= 3, is(true));

        productRepo.deleteAll(saved);
    }

}