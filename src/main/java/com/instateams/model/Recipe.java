package com.instateams.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe implements Comparable<Recipe>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd") //This is the pattern of the date sent by the HTML form
    private LocalDate startDate;

    @ManyToMany
    private List<Ingredient> ingredientsNeeded;

    @ManyToMany
    private List<Portion> portions;

    public Recipe()
    {
        this(null, null, null, null);
    }

    public Recipe(String name, String description, Status status, LocalDate startDate)
    {
        this.name = name;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.ingredientsNeeded = new ArrayList<>();
        this.portions = new ArrayList<>();
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public List<Ingredient> getIngredientsNeeded()
    {
        return ingredientsNeeded;
    }

    public void setIngredientsNeeded(List<Ingredient> ingredientsNeeded)
    {
        this.ingredientsNeeded = ingredientsNeeded;
    }

    public List<Portion> getPortions()
    {
        return portions;
    }

    public void setPortions(List<Portion> portions)
    {
        this.portions = portions;
    }

    public List<Ingredient> getEmptyIngredients()
    {
        List<Ingredient> emptyIngredients = new ArrayList<>(ingredientsNeeded);
        portions.forEach(portion -> emptyIngredients.remove(portion.getIngredient()));

        return emptyIngredients;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Recipe recipe = (Recipe)o;

        if (id != null ? !id.equals(recipe.id) : recipe.id != null)
            return false;
        return name != null ? name.equals(recipe.name) : recipe.name == null;

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
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", ingredientsNeeded=" + ingredientsNeeded +
                '}';
    }

    @Override
    public int compareTo(Recipe o)
    {
        return this.startDate.compareTo(o.startDate);
    }
}
