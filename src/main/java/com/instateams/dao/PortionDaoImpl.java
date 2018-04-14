package com.instateams.dao;

import com.instateams.model.Ingredient;
import com.instateams.model.Portion;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PortionDaoImpl extends GenericDao<Portion> implements PortionDao
{
    @Override
    protected Class<Portion> getPersistentClass()
    {
        return Portion.class;
    }

    @Override
    public List<Portion> findByIngredient(Ingredient ingredient)
    {
        Session session = getSessionFactory().openSession();

        TypedQuery<Portion> query = session
                .createQuery("select portion from Portion portion where portion.ingredient = :ingredient",
                        Portion.class)
                .setParameter("ingredient", ingredient);

        List<Portion> portions = query.getResultList();

        session.close();

        return portions;
    }
}
