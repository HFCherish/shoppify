package com.tw.shoppify.inventory.api;

import com.tw.shoppify.inventory.appservice.InventoryService;
import com.tw.shoppify.inventory.appservice.Product;
import com.tw.shoppify.inventory.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
public class InventoryApi {
    @Autowired
    InventoryService inventoryService;

    @Autowired
    Routes routes;

    private Product product;

    public InventoryApi setProduct(Product product) {
        this.product = product;
        return this;
    }

    @Path("inventories")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveInventory(Inventory inventory) {
        Inventory save = inventoryService.save(product, inventory);

        return Response.created(URI.create(routes.inventoryUrl(save))).build();
    }

}
