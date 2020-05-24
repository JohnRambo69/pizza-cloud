package com.rambosoftware.pizzacloud.repositories;

import com.rambosoftware.pizzacloud.domain.Order;
import com.rambosoftware.pizzacloud.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository
        extends ReactiveCrudRepository<Order, String> {

    Flux<Order> findByUserOrderByPlacedAtDesc(
            User user, Pageable pageable);

}
