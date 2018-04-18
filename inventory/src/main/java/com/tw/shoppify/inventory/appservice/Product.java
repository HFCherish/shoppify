package com.tw.shoppify.inventory.appservice;

import com.alibaba.fastjson.JSONObject;
import com.tw.stringutils.IdGenerator;

/**
 * @author hf_cherish
 * @date 4/18/18
 */
public class Product {
    public static final String STORE_FIELD = "store";
    public static final String NAME_FIELD = "name";
    public static final String ID_FIELD = "id";
    private JSONObject data;

    public Product(JSONObject data) {
        this.data = data;
    }

    public Product(String id) {
        this(new JSONObject().fluentPut(ID_FIELD, id));
    }

    public Product(String name, String store) {
        this(new JSONObject()
                .fluentPut(ID_FIELD, IdGenerator.next())
                .fluentPut(NAME_FIELD, name)
                .fluentPut(STORE_FIELD, store));
    }

    public JSONObject getAllData() {
        return data;
    }

    public String getId() {
        return data.getString(ID_FIELD);
    }

    public String getName() {
        return data.getString(NAME_FIELD);
    }

    public String getStore() {
        return data.getString(STORE_FIELD);
    }
}
