package com.instateams.dao;

import com.instateams.model.Portion;
import com.instateams.model.Recipe;
import com.instateams.model.Ingredient;

import java.util.List;

public interface RecipeDao
{
    List<Recipe> findAll();

    List<Recipe> findByPortion(Portion portion);
    List<Recipe> findByIngredient(Ingredient ingredient);
    Recipe findById(Long id);
    void save(Recipe recipe);
    void delete(Recipe recipe);
}
