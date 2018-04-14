package com.instateams.service;

import com.instateams.dao.RecipeDao;
import com.instateams.exception.ObjectNotFoundException;
import com.instateams.model.Portion;
import com.instateams.model.Recipe;
import com.instateams.model.Ingredient;
import com.instateams.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService
{
    @Autowired
    private RecipeDao recipeDao;

    @Override
    public List<Recipe> findAll()
    {
        List<Recipe> recipes = recipeDao.findAll();
        Collections.sort(recipes);
        return recipes;
    }

    @Override
    public List<Recipe> findByPortion(Portion portion)
    {
        return recipeDao.findByPortion(portion);
    }

    @Override
    public List<Recipe> findByIngredient(Ingredient ingredient)
    {
        return recipeDao.findByIngredient(ingredient);
    }

    @Override
    public List<Status> allStatus()
    {
        return Arrays.asList(Status.values());
    }

    @Override
    public Recipe findById(Long id)
    {
        Recipe recipe = recipeDao.findById(id);
        if (recipe == null)
        {
            throw new ObjectNotFoundException("Recipe was not found");
        }
        return recipe;
    }

    @Override
    public void unassignIngredient(Long recipeId, Ingredient ingredient)
    {
        Recipe recipe = findById(recipeId);
        //remove portion with that ingredient assigned to the recipe
        Portion portionWithIngredient = recipe.getPortions()
                .stream()
                .filter(portion -> portion.getIngredient().equals(ingredient))
                .findFirst()
                .orElse(null);

        if (portionWithIngredient != null)
        {
            unassignPortion(recipeId, portionWithIngredient);
        }

        //remove the ingredient
        recipe.getIngredientsNeeded().remove(ingredient);

        recipeDao.save(recipe);
    }

    public void unassignPortion(Long recipeId, Portion portion)
    {
        Recipe recipe = findById(recipeId);
        recipe.getPortions().remove(portion);
        recipeDao.save(recipe);
    }

    @Override
    public void save(Recipe recipe)
    {
        recipeDao.save(recipe);
    }

    @Override
    public void delete(Recipe recipe)
    {
        recipeDao.delete(recipe);
    }
}
