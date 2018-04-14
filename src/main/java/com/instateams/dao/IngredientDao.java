package com.instateams.dao;

import com.instateams.model.Ingredient;

import java.util.List;

public interface IngredientDao
{
    List<Ingredient> findAll();
    Ingredient findById(Long id);
    void save(Ingredient ingredient);
    void delete(Ingredient ingredient);
}
