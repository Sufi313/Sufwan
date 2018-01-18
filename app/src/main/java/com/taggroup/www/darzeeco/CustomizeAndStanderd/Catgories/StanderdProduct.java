package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;

/**
 * Created by muhammad.sufwan on 1/15/2018.
 */

public class StanderdProduct {

    private int id;
    private String type;
    private String name;
    private String description;
    private float amount;
    private String image;

    public StanderdProduct(int id, String type, String name, String description, float amount, String image) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getAmount() {
        return amount;
    }

    public String getImage() {
        return image;
    }
}
