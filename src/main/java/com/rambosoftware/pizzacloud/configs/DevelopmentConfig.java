package com.rambosoftware.pizzacloud.configs;

import com.rambosoftware.pizzacloud.domain.Ingredient;
import com.rambosoftware.pizzacloud.domain.Ingredient.Type;
import com.rambosoftware.pizzacloud.repositories.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("product")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo
                                        ) {

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

                System.out.println("DATA LOADING ~~~~~~~~~~~~~~~~~~~~~~~~~");

            }

            private Ingredient saveAnIngredient(String id, String name, Type type) {
                Double price = 0.5;
                Ingredient ingredient = new Ingredient(id, name, type, price);
                repo.save(ingredient).subscribe();
                return ingredient;
            }
        };
    }
}
