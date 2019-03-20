package hotswap.test.jaxrs.ping;

import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;

import javax.inject.Inject;

import static io.vertx.core.http.HttpMethod.GET;

public class PingResource {

    @Inject
    private Statistics statistics;

    @Route(path = "/ping/simple", methods = GET)
    void getSimple(RoutingContext context) {
        //statistics.updateStatistic(0);
        context.response().setStatusCode(200).end();
    }
}
