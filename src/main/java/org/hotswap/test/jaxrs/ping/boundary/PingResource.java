package org.hotswap.test.jaxrs.ping.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hotswap.test.jaxrs.ping.control.Statistics;

@Path("ping")
@ApplicationScoped
public class PingResource {

    @Inject
    private Statistics statistics;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("simple")
    public Response getSimple() {
        statistics.updateStatistic(0);
        return Response.ok().build();
    }

    @GET
    @Path("/pathparam/{param:.+}")
    @Produces(MediaType.TEXT_PLAIN )
    public Response getStatistic(@PathParam("param") String ua) {
        statistics.updateStatistic(0);
        return Response.ok().build();
    }
}