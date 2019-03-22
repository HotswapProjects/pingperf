package org.hotswap.test.dropwizard.ping;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PingPerfApplication extends Application<PingPerfConfiguration> {
    public static void main(String[] args) throws Exception {
        new PingPerfApplication().run(args);
    }

    @Override
    public String getName() {
        return "pingperf-dropwizard";
    }

    @Override
    public void initialize(Bootstrap<PingPerfConfiguration> bootstrap) {
    }

    @Override
    public void run(PingPerfConfiguration configuration, Environment environment) {
        environment.jersey().register(new PingResource());
    }
}
