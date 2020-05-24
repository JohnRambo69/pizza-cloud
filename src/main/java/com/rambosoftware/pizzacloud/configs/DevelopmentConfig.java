package com.rambosoftware.pizzacloud.configs;

import com.rambosoftware.pizzacloud.domain.Ingredient;
import com.rambosoftware.pizzacloud.domain.Ingredient.Type;
import com.rambosoftware.pizzacloud.domain.PaymentMethod;
import com.rambosoftware.pizzacloud.domain.Pizza;
import com.rambosoftware.pizzacloud.domain.User;
import com.rambosoftware.pizzacloud.repositories.IngredientRepository;
import com.rambosoftware.pizzacloud.repositories.PaymentMethodRepository;
import com.rambosoftware.pizzacloud.repositories.PizzaRepository;
import com.rambosoftware.pizzacloud.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

//@Profile("product")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository,
                                        UserRepository userRepo, PasswordEncoder encoder, PizzaRepository tacoRepo,
                                        PaymentMethodRepository paymentMethodRepo) { // user repo for ease of testing with a built-in user

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Ingredient flourTortilla = saveAnIngredient("FLTO", "Flour Tortilla", Type.DOUGH);
                Ingredient cornTortilla = saveAnIngredient("COTO", "Corn Tortilla", Type.DOUGH);
                Ingredient groundBeef = saveAnIngredient("GRBF", "Ground Beef", Type.MEAT);
                Ingredient carnitas = saveAnIngredient("CARN", "Carnitas", Type.MEAT);
                Ingredient tomatoes = saveAnIngredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
                Ingredient lettuce = saveAnIngredient("LETC", "Lettuce", Type.VEGGIES);
                Ingredient cheddar = saveAnIngredient("CHED", "Cheddar", Type.CHEESE);
                Ingredient jack = saveAnIngredient("JACK", "Monterrey Jack", Type.CHEESE);
                Ingredient salsa = saveAnIngredient("SLSA", "Salsa", Type.SAUCE);
                Ingredient sourCream = saveAnIngredient("SRCR", "Sour Cream", Type.SAUCE);


                userRepo.save(new User("habuma", encoder.encode("password"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234", "craig@habuma.com"))
                        .subscribe(user -> {
                            paymentMethodRepo.save(new PaymentMethod(user, "4111111111111111", "321", "10/25")).subscribe();
                        });

                Pizza pizza1 = new Pizza();
                pizza1.setId("TACO1");
                pizza1.setName("Carnivore");
                pizza1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
                tacoRepo.save(pizza1).subscribe();

                Pizza pizza2 = new Pizza();
                pizza2.setId("TACO2");
                pizza2.setName("Bovine Bounty");
                pizza2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
                tacoRepo.save(pizza2).subscribe();

                Pizza pizza3 = new Pizza();
                pizza3.setId("TACO3");
                pizza3.setName("Veg-Out");
                pizza3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
                tacoRepo.save(pizza3).subscribe();


                System.out.println("DATA LOADING ~~~~~~~~~~~~~~~~~~~~~~~~~");

            }

            private Ingredient saveAnIngredient(String id, String name, Type type) {
                Double price = 0.5;
                Ingredient ingredient = new Ingredient(id, name, type, price);
                ingredientRepository.save(ingredient).subscribe();
                return ingredient;
            }
        };
    }
}
