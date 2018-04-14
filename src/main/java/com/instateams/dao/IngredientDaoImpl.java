package com.instateams.dao;


import com.instateams.model.Ingredient;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientDaoImpl extends GenericDao<Ingredient> implements IngredientDao
{
    @Override
    protected Class<Ingredient> getPersistentClass()
    {
        return Ingredient.class;
    }

    @Override
    public Ingredient findById(Long id)
    {
        Session session = getSessionFactory().openSession();
        Ingredient ingredient = session.get(Ingredient.class, id);

        if (ingredient != null)
        {
            Hibernate.initialize(ingredient.getPortions());
        }

        session.close();

        return ingredient;
    }
}
