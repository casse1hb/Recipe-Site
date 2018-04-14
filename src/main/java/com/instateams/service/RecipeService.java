package com.instateams.service;

import com.instateams.model.Portion;
import com.instateams.model.Recipe;
import com.instateams.model.Ingredient;
import com.instateams.model.Status;

import java.util.List;

public interface RecipeService
{
    List<Recipe> findAll();

    List<Recipe> findByPortion(Portion portion);

    List<Recipe> findByIngredient(Ingredient ingredient);

    List<Status> allStatus();

    Recipe findById(Long id);

    void unassignIngredient(Long recipeId, Ingredient ingredient);

    void unassignPortion(Long recipeId, Portion portion);

    void save(Recipe recipe);

    void delete(Recipe recipe);
}
