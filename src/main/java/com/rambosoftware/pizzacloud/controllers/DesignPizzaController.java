package com.rambosoftware.pizzacloud.controllers;

import com.rambosoftware.pizzacloud.domain.Pizza;
import com.rambosoftware.pizzacloud.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignPizzaController {

    private PizzaRepository pizzaRepository;

    @Autowired
    public DesignPizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/recent")
    public Flux<Pizza> recentTacos() {
        return pizzaRepository.findAll().take(12);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pizza> postTaco(@RequestBody Pizza taco) {
        return pizzaRepository.save(taco);
    }

    @GetMapping("/{id}")
    public Mono<Pizza> tacoById(@PathVariable("id") String id) {
        return pizzaRepository.findById(id);
    }

}
