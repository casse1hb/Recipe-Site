package com.instateams.service;

import com.instateams.model.Portion;
import com.instateams.model.Ingredient;

import java.util.List;

public interface PortionService
{
    List<Portion> findAll();

    public List<Portion> findByIngredient(Ingredient ingredient);
    Portion findById(Long id);
    void save(Portion portion);

    void unassignIngredient(Portion portion);
    void delete(Portion portion);
}
