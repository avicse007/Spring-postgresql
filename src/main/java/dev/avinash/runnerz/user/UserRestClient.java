package dev.avinash.runnerz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {
    private final RestClient restClient;

    public UserRestClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                                  .baseUrl("https://jsonplaceholder.typicode.com/")
                                  .build();
    }

    public List<User> findAll() {
        return restClient.get()
                       .uri("/users")
                       .retrieve()
                       .body(new ParameterizedTypeReference<List<User>>() {
                       });
    }

    public User findById(String id) {
        return restClient.get()
                       .uri("/user/{id}", id)
                       .retrieve()
                       .body(User.class);
    }


}
