package dev.avinash.runnerz;

import dev.avinash.runnerz.run.Location;
import dev.avinash.runnerz.run.Run;
import dev.avinash.runnerz.user.User;
import dev.avinash.runnerz.user.UserHttpClient;
import dev.avinash.runnerz.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootApplication
public class RunnerzApplication {
    private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RunnerzApplication.class, args);
        log.info("Application started");
        log.info("Application started Successfully");

    }

    @Bean
    UserHttpClient userHttpClient() {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserHttpClient.class);
    }

    @Bean
    CommandLineRunner runner(UserRestClient client, UserHttpClient httpClient) {
        return args -> {
            log.info("CommandLineRunner started");
            Run run = new Run(Integer.valueOf(1), "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 1, Location.OUTDOOR, Integer.valueOf(0));
            log.info("Run is :" + run);
            List<User> users = client.findAll();
            log.info("All users ");
            log.info(String.valueOf(users));
            log.info("All users from HttpClient");
            log.info(httpClient.findAll().toString());
            //log.info(httpClient.findById(2).toString());

        };
    }

}
