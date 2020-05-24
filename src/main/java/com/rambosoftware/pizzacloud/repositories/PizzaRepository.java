package com.rambosoftware.pizzacloud.repositories;

import com.rambosoftware.pizzacloud.domain.Pizza;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PizzaRepository
        extends ReactiveCrudRepository<Pizza, String> {

}
