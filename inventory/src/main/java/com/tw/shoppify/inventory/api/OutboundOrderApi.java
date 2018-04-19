package com.tw.shoppify.inventory.api;

import com.tw.shoppify.inventory.appservice.OutboundOrderService;
import com.tw.shoppify.inventory.domain.OutboundOrder;
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
@Path("/outbound-orders")
public class OutboundOrderApi {
    @Autowired
    OutboundOrderService outboundOrderService;

    @Autowired
    Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveOutboundOrder(OutboundOrder inventory) {
        OutboundOrder save = outboundOrderService.save(inventory);

        return Response.created(URI.create(routes.outboundOrderUrl(save))).build();
    }
}
