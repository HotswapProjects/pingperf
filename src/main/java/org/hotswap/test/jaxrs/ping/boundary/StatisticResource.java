package org.hotswap.test.jaxrs.ping.boundary;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hotswap.test.jaxrs.ping.control.Statistics;

@Path("statistic")
public class StatisticResource {

    @Inject
    private Statistics statistics;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistic() {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        jsonBuilder.add("total_requests", statistics.getTotalRequests())
            .add("total_nanos", statistics.getTotalNanos())
            .add("avg_throughput", statistics.getAvgThroughput())
            ;

        return Response.ok(jsonBuilder.build().toString()).build();
    }
}