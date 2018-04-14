package com.instateams.service;

import com.instateams.model.Ingredient;

import java.util.List;

public interface IngredientService
{
    List<Ingredient> findAll();

    Ingredient findById(Long id);

    void save(Ingredient ingredient);

    void delete(Ingredient ingredient);
}
