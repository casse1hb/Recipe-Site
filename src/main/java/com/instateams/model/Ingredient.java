package com.instateams.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient implements Comparable<Ingredient>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @OneToMany(mappedBy = "ingredient")
    private List<Portion> portions;

    public Ingredient()
    {
        this(null);
    }

    public Ingredient(String name)
    {
        this.name = name;
        portions = new ArrayList<>();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Portion> getPortions()
    {
        return portions;
    }

    public void setPortions(List<Portion> portions)
    {
        this.portions = portions;
    }

    public boolean hasPortions()
    {
        return !(portions == null || portions.isEmpty());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Ingredient ingredient = (Ingredient)o;

        if (id != null ? !id.equals(ingredient.id) : ingredient.id != null)
            return false;
        return name != null ? name.equals(ingredient.name) : ingredient.name == null;

    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Ingredient o)
    {
        return this.getName().compareTo(o.getName());
    }
}
