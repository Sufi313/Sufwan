package com.taggroup.www.darzeeco.ProductsAndContents;

/**
 * Created by muhammad.sufwan on 11/28/2017.
 */
public class Product {
    private int id;
    private String title;
    private String shortdesc;
    private String rating;
    private float price;
    private String image;

    public Product(int id, String title, String shortdesc, String rating, float price, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getRating() {
        return rating;
    }

    public float getPrice() {
        return  price;
    }

    public String getImage() {
        return image;
    }
}