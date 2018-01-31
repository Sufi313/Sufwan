package com.taggroup.www.darzeeco.CustomerAct;

/**
 * Created by muhammad.sufwan on 1/26/2018.
 */

public class CustomCart {

    int id, size_id, user_id;
    String design_id, design_Type;
    float order_amount;

    public CustomCart(int id, int size_id, int user_id, String design_id, String design_Type, float order_amount) {
        this.id = id;
        this.size_id = size_id;
        this.user_id = user_id;
        this.design_id = design_id;
        this.design_Type = design_Type;
        this.order_amount = order_amount;
    }

    public int getId() {
        return id;
    }

    public int getSize_id() {
        return size_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getDesign_id() {
        return design_id;
    }

    public String getDesign_Type() {
        return design_Type;
    }

    public float getOrder_amount() {
        return order_amount;
    }
}
