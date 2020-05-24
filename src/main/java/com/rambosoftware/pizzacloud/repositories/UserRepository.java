package com.rambosoftware.pizzacloud.repositories;

import com.rambosoftware.pizzacloud.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByUsername(String username);

    Mono<User> findByEmail(String email);

}
