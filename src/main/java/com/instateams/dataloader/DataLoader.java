package com.instateams.dataloader;

import com.instateams.model.Portion;
import com.instateams.model.Recipe;
import com.instateams.model.Ingredient;
import com.instateams.model.Status;
import com.instateams.service.PortionService;
import com.instateams.service.RecipeService;
import com.instateams.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner
{
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private PortionService portionService;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Onion"),
                new Ingredient("Bell Pepper"),
                new Ingredient("Tomato"),
                new Ingredient("Rice"),
                new Ingredient("Lemon"),
                new Ingredient("Water"),
                new Ingredient("Garlic"),
                new Ingredient("Eggs ~ 1/4 cup per egg"),
                new Ingredient("Salt"),
                new Ingredient("Pepper"),
                new Ingredient("Black Beans"),
                new Ingredient("Chic Peas"),
                new Ingredient("Sugar")
        );
        ingredients.forEach(ingredientService::save);

        Arrays.asList(
                //0
                new Portion("A pinch", ingredients.get(0
                )),
                new Portion("1/4 Cup", ingredients.get(0)),
                new Portion("1/2 Cup", ingredients.get(0)),
                new Portion("1/3 Cup", ingredients.get(0)),
                new Portion("3/4 Cup", ingredients.get(0)),
                new Portion("1 1/4 Cup", ingredients.get(0)),
                new Portion("1 2/4 Cup", ingredients.get(0)),
                new Portion("1 3/4 Cup", ingredients.get(0)),
                new Portion("8 oz", ingredients.get(0)),
                //1
                new Portion("A pinch", ingredients.get(1
                )),
                new Portion("1/4 Cup", ingredients.get(1)),
                new Portion("1/2 Cup", ingredients.get(1)),
                new Portion("1/3 Cup", ingredients.get(1)),
                new Portion("3/4 Cup", ingredients.get(1)),
                new Portion("1 1/4 Cup", ingredients.get(1)),
                new Portion("1 2/4 Cup", ingredients.get(1)),
                new Portion("1 3/4 Cup", ingredients.get(1)),
                new Portion("8 oz", ingredients.get(1)),
                new Portion("16 oz", ingredients.get(1)),

             //3
                new Portion("A pinch", ingredients.get(3
                )),
                new Portion("1/4 Cup", ingredients.get(3)),
                new Portion("1/2 Cup", ingredients.get(3)),
                new Portion("1/3 Cup", ingredients.get(3)),
                new Portion("3/4 Cup", ingredients.get(3)),
                new Portion("1 1/4 Cup", ingredients.get(3)),
                new Portion("1 2/4 Cup", ingredients.get(3)),
                new Portion("1 3/4 Cup", ingredients.get(3)),
                new Portion("8 oz", ingredients.get(3)),
                new Portion("16 oz", ingredients.get(3)),

                //4
                new Portion("A pinch", ingredients.get(4
                )),
                new Portion("1/4 Cup", ingredients.get(4)),
                new Portion("1/2 Cup", ingredients.get(4)),
                new Portion("1/3 Cup", ingredients.get(4)),
                new Portion("3/4 Cup", ingredients.get(4)),
                new Portion("1 1/4 Cup", ingredients.get(4)),
                new Portion("1 2/4 Cup", ingredients.get(4)),
                new Portion("1 3/4 Cup", ingredients.get(4)),
                new Portion("8 oz", ingredients.get(4)),
                new Portion("16 oz", ingredients.get(4)),

                //5
                new Portion("A pinch", ingredients.get(5
                )),
                new Portion("1/4 Cup", ingredients.get(5)),
                new Portion("1/2 Cup", ingredients.get(5)),
                new Portion("1/3 Cup", ingredients.get(5)),
                new Portion("3/4 Cup", ingredients.get(5)),
                new Portion("1 1/4 Cup", ingredients.get(5)),
                new Portion("1 2/4 Cup", ingredients.get(5)),
                new Portion("1 3/4 Cup", ingredients.get(5)),
                new Portion("8 oz", ingredients.get(5)),
                new Portion("16 oz", ingredients.get(5)),

                //6
                new Portion("A pinch", ingredients.get(6
                )),
                new Portion("1/4 Cup", ingredients.get(6)),
                new Portion("1/2 Cup", ingredients.get(6)),
                new Portion("1/3 Cup", ingredients.get(6)),
                new Portion("3/4 Cup", ingredients.get(6)),
                new Portion("1 1/4 Cup", ingredients.get(6)),
                new Portion("1 2/4 Cup", ingredients.get(6)),
                new Portion("1 3/4 Cup", ingredients.get(6)),
                new Portion("8 oz", ingredients.get(6)),
                new Portion("16 oz", ingredients.get(6)),

                new Portion("A pinch", ingredients.get(7
                )),
                new Portion("1/4 Cup", ingredients.get(7)),
                new Portion("1/2 Cup", ingredients.get(7)),
                new Portion("1/3 Cup", ingredients.get(7)),
                new Portion("3/4 Cup", ingredients.get(7)),
                new Portion("1 1/4 Cup", ingredients.get(7)),
                new Portion("1 2/4 Cup", ingredients.get(7)),
                new Portion("1 3/4 Cup", ingredients.get(7)),
                new Portion("8 oz", ingredients.get(7)),
                new Portion("16 oz", ingredients.get(7)),

                //8
                new Portion("A pinch", ingredients.get(8
                )),
                new Portion("1/4 Cup", ingredients.get(8)),
                new Portion("1/2 Cup", ingredients.get(8)),
                new Portion("1/3 Cup", ingredients.get(8)),
                new Portion("3/4 Cup", ingredients.get(8)),
                new Portion("1 1/4 Cup", ingredients.get(8)),
                new Portion("1 2/4 Cup", ingredients.get(8)),
                new Portion("1 3/4 Cup", ingredients.get(8)),
                new Portion("8 oz", ingredients.get(8)),
                new Portion("16 oz", ingredients.get(8)),

                new Portion("A pinch", ingredients.get(9
                )),
                new Portion("1/4 Cup", ingredients.get(9)),
                new Portion("1/2 Cup", ingredients.get(9)),
                new Portion("1/3 Cup", ingredients.get(9)),
                new Portion("3/4 Cup", ingredients.get(9)),
                new Portion("1 1/4 Cup", ingredients.get(9)),
                new Portion("1 2/4 Cup", ingredients.get(9)),
                new Portion("1 3/4 Cup", ingredients.get(9)),
                new Portion("8 oz", ingredients.get(9)),
                new Portion("16 oz", ingredients.get(9)),

                //10
                new Portion("A pinch", ingredients.get(10
                )),
                new Portion("1/4 Cup", ingredients.get(10)),
                new Portion("1/2 Cup", ingredients.get(10)),
                new Portion("1/3 Cup", ingredients.get(10)),
                new Portion("3/4 Cup", ingredients.get(10)),
                new Portion("1 1/4 Cup", ingredients.get(10)),
                new Portion("1 2/4 Cup", ingredients.get(10)),
                new Portion("1 3/4 Cup", ingredients.get(10)),
                new Portion("8 oz", ingredients.get(10)),
                new Portion("16 oz", ingredients.get(10)),

                //11
                new Portion("A pinch", ingredients.get(11
                )),
                new Portion("1/4 Cup", ingredients.get(11)),
                new Portion("1/2 Cup", ingredients.get(11)),
                new Portion("1/3 Cup", ingredients.get(11)),
                new Portion("3/4 Cup", ingredients.get(11)),
                new Portion("1 1/4 Cup", ingredients.get(11)),
                new Portion("1 2/4 Cup", ingredients.get(11)),
                new Portion("1 3/4 Cup", ingredients.get(11)),
                new Portion("8 oz", ingredients.get(11)),
                new Portion("16 oz", ingredients.get(11)),

                //12
                new Portion("A pinch", ingredients.get(12
                )),
                new Portion("1/4 Cup", ingredients.get(12)),
                new Portion("1/2 Cup", ingredients.get(12)),
                new Portion("1/3 Cup", ingredients.get(12)),
                new Portion("3/4 Cup", ingredients.get(12)),
                new Portion("1 1/4 Cup", ingredients.get(12)),
                new Portion("1 2/4 Cup", ingredients.get(12)),
                new Portion("1 3/4 Cup", ingredients.get(12)),
                new Portion("8 oz", ingredients.get(12)),
                new Portion("16 oz", ingredients.get(12))

                ).forEach(portionService::save);


        Arrays.asList(
                new Recipe("Tomato Soup", "A simple recipe for delicious tomato soup.", Status.ARCHIVED,
                        LocalDate.of(2018, 2, 3)),
                new Recipe("Joe's Eggs over Rice", "Joe's world famous eggs with rice and a secret ingredient passed down for generations.", Status.RUNNING,
                        LocalDate.of(2017, 5, 11))
        ).forEach(recipeService::save);
    }
}
