package org.hotswap.test.jaxrs.ping;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ping")
public class PingResource {

    @Inject
    private Statistics statistics;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("simple")
    public Response getSimple() {
        //statistics.updateStatistic(0);
        return Response.ok().build();
    }
}