package org.hotswap.test.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@Autowired
	private Statistics statistics;

    @RequestMapping("/simple")
    public String home() {
        //statistics.updateStatistic(0);
        return "ok";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
