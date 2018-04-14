package com.instateams.dao;

import com.instateams.model.Portion;
import com.instateams.model.Recipe;
import com.instateams.model.Ingredient;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RecipeDaoImpl extends GenericDao<Recipe> implements RecipeDao
{
    @Override
    protected Class<Recipe> getPersistentClass()
    {
        return Recipe.class;
    }

    @Override
    public List<Recipe> findByPortion(Portion portion)
    {
        Session session = getSessionFactory().openSession();

        TypedQuery<Recipe> query = session
                .createQuery("select distinct recipe from Recipe recipe join recipe.portions portion " +
                        "where portion = :portion", Recipe.class)
                .setParameter("portion", portion);

        List<Recipe> recipes = query.getResultList();

        session.close();

        return recipes;
    }

    public List<Recipe> findByIngredient(Ingredient ingredient)
    {
        Session session = getSessionFactory().openSession();

        TypedQuery<Recipe> query = session
                .createQuery("select distinct recipe from Recipe recipe join recipe.ingredientsNeeded ingredient where ingredient =" +
                                " :ingredient",
                        Recipe.class)
                .setParameter("ingredient", ingredient);

        List<Recipe> recipes = query.getResultList();

        session.close();

        return recipes;
    }

    @Override
    public Recipe findById(Long id)
    {
        Session session = getSessionFactory().openSession();
        Recipe recipe = session.get(Recipe.class, id);

        if (recipe != null)
        {
            Hibernate.initialize(recipe.getIngredientsNeeded());
            Hibernate.initialize(recipe.getPortions());
            recipe.getIngredientsNeeded().forEach(ingredient -> Hibernate.initialize(ingredient.getPortions()));
        }

        session.close();

        return recipe;
    }
}
