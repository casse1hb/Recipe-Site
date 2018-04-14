package com.instateams.model;


public enum Status
{
    RUNNING("Breakfast", "active"),
    ARCHIVED("Lunch", "inactive"),
    NOT_STARTED("Dinner", "inactive");

    private String name;
    private String state;

    Status(String name, String state)
    {
        this.name = name;
        this.state = state;
    }

    public String getName()
    {
        return name;
    }

    public String getState()
    {
        return state;
    }
}
