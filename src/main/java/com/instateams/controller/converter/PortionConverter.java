package com.instateams.controller.converter;

import com.instateams.dao.PortionDao;
import com.instateams.model.Portion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class
PortionConverter implements Converter<String, Portion>
{
    @Autowired
    private PortionDao portionDao;

    @Override
    public Portion convert(String source)
    {
        return portionDao.findById(Long.parseLong(source));
    }

    @Bean
    public ConversionService getConversionService()
    {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new PortionConverter());
        bean.setConverters(converters);
        return bean.getObject();
    }
}
