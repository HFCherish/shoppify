package com.tw.shoppify.pricing.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tw.stringutils.IdGenerator;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE)
@JsonInclude(NON_NULL)
public class Product implements Serializable {
    private String id;
    private String name;
    private String store;

    private Product() {
    }

    public Product(String name, String store) {
        this.name = name;
        this.store = store;
        this.id = IdGenerator.next();
    }

    public Product(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStore() {
        return store;
    }
}
