package com.rambosoftware.pizzacloud.repositories;

import com.rambosoftware.pizzacloud.domain.Ingredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

//@Repository
@CrossOrigin(origins="*")
public interface IngredientRepository
        extends ReactiveCrudRepository<Ingredient, String> {

}
