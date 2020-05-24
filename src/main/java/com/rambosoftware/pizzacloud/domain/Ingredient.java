package com.rambosoftware.pizzacloud.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@RequiredArgsConstructor
//@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Document(collection = "ingredients")
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;
    private final Double price;

    public static enum Type {
        DOUGH, MEAT, VEGGIES, CHEESE, SAUCE
    }

}
