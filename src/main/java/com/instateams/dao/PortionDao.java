package com.instateams.dao;

import com.instateams.model.Portion;
import com.instateams.model.Ingredient;

import java.util.List;

public interface PortionDao
{
    List<Portion> findAll();

    List<Portion> findByIngredient(Ingredient ingredient);
    Portion findById(Long id);
    void save(Portion portion);
    void delete(Portion portion);
}
