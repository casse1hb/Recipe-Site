package com.instateams.service;

import com.instateams.dao.PortionDao;
import com.instateams.exception.ObjectNotFoundException;
import com.instateams.model.Portion;
import com.instateams.model.Recipe;
import com.instateams.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortionServiceImpl implements PortionService
{
    @Autowired
    private PortionDao portionDao;

    @Autowired
    private RecipeService recipeService;

    @Override
    public List<Portion> findAll()
    {
        return portionDao.findAll();
    }

    @Override
    public List<Portion> findByIngredient(Ingredient ingredient)
    {
        return portionDao.findByIngredient(ingredient);
    }


    @Override
    public Portion findById(Long id)
    {
        Portion portion = portionDao.findById(id);
        if (portion == null)
        {
            throw new ObjectNotFoundException("Portion was not found");
        }
        return portion;
    }

    @Override
    public void save(Portion portion)
    {
        portionDao.save(portion);
    }

    @Override
    public void unassignIngredient(Portion portion)
    {
        portion.setIngredient(null);
        portionDao.save(portion);
    }

    @Override
    public void delete(Portion portion)
    {
        List<Recipe> recipesWithPortion = recipeService.findByPortion(portion);
        if (!(recipesWithPortion == null || recipesWithPortion.isEmpty()))
        {
            recipesWithPortion.forEach(recipe -> recipeService.unassignPortion(recipe.getId(), portion));
        }

        portionDao.delete(portion);
    }
}
