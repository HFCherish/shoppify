package com.tw.shoppify.inventory.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author hf_cherish
 * @date 4/17/18
 */
@Path("/echo")
public class EchoApi {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "hello, this is inventory service";
    }
}
