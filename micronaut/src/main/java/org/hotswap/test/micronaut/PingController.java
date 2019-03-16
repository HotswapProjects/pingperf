package org.hotswap.test.micronaut;

import javax.inject.Inject;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;
import io.reactivex.Single;

@Controller("/")
@Validated
public class PingController {

	@Inject
	Statistics statistics;;

    @Get(uri = "/simple", produces = MediaType.TEXT_PLAIN)
    public Single<String> hello() {
        return Single.just("ok");
    }
}
