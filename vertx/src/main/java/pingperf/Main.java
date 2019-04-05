package pingperf;

import io.vertx.core.AbstractVerticle;

public class Main extends AbstractVerticle {

    @Override
    public void start() {
        vertx
        .createHttpServer()
        .requestHandler(req -> {
            if ("/simple".equals(req.path())) {
                req.response()
                    .end();
            } else {
                req.response()
                    .setStatusCode(404)
                    .end();
            }
        })
        .listen(8080);
    }

}