package com.taggroup.www.darzeeco.CustomerAct;

/**
 * Created by muhammad.sufwan on 12/15/2017.
 */

public class Cart {

    private int id;

    private int size_id;
    private String design_type;
    private String design_id;
    private float price;
    private int user_id;
    private String image;

    public Cart(int id, int user_id, int size_id, String design_id, String design_type, float price, String image) {
        this.id = id;
        this.size_id = size_id;
        this.design_type = design_type;
        this.design_id = design_id;
        this.price = price;
        this.user_id = user_id;
        this.image = image;
    }

    public int getId() {
        return id;
    }


    public int getSize_id() {
        return size_id;
    }

    public String getDesign_type() {
        return design_type;
    }

    public String getDesign_id() {
        return design_id;
    }

    public float getPrice() {
        return price;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getImage() {
        return image;
    }
}
