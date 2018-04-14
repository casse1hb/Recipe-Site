package com.instateams.service;

import com.instateams.dao.IngredientDao;
import com.instateams.exception.ObjectNotFoundException;
import com.instateams.model.Ingredient;
import com.instateams.model.Portion;
import com.instateams.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService
{
    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private PortionService portionService;

    @Autowired
    private RecipeService recipeService;

    @Override
    public List<Ingredient> findAll()
    {
        return ingredientDao.findAll();
    }

    @Override
    public Ingredient findById(Long id)
    {
        Ingredient ingredient = ingredientDao.findById(id);
        if (ingredient == null)
        {
            throw new ObjectNotFoundException("Ingredient was not found");
        }
        return ingredientDao.findById(id);
    }

    @Override
    public void save(Ingredient ingredient)
    {
        ingredientDao.save(ingredient);
    }

    @Override
    public void delete(Ingredient ingredient)
    {
        List<Recipe> recipesWithIngredient = recipeService.findByIngredient(ingredient);
        if (!(recipesWithIngredient == null || recipesWithIngredient.isEmpty()))
        {
            recipesWithIngredient.forEach(recipe -> recipeService.unassignIngredient(recipe.getId(), ingredient));
        }

        if (ingredient.hasPortions())
        {
            List<Portion> portions = portionService.findByIngredient(ingredient);
            portions.forEach(portionService::unassignIngredient);
        }

        ingredientDao.delete(ingredient);
    }
}
