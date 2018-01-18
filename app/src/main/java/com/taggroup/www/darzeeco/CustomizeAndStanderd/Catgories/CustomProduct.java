package com.taggroup.www.darzeeco.CustomizeAndStanderd.Catgories;

/**
 * Created by muhammad.sufwan on 1/17/2018.
 */

public class CustomProduct {

    int id;
    String name;
    float price;
    String image;


    public CustomProduct(int id, String name, float price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice(){
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
