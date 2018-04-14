package com.instateams.controller.converter;

import com.instateams.dao.IngredientDao;
import com.instateams.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class IngredientConverter implements Converter<String, Ingredient>
{
    @Autowired
    private IngredientDao ingredientDao;

    @Override
    public Ingredient convert(String source)
    {
        return ingredientDao.findById(Long.parseLong(source));
    }

    @Bean
    public ConversionService getConversionService()
    {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new IngredientConverter());
        bean.setConverters(converters);
        return bean.getObject();
    }
}
