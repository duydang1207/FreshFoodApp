package com.example.freshfoodapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Cart")
public class CartEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int promotion;
    private String image;

    public CartEntity(String name, int quantity, double price, int promotion, String image) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.promotion = promotion;
        this.image = image;
    }

//    public CartEntity(String name, int quantity, double price, int promotion, String image) {
//        this.name = name;
//        this.quantity = quantity;
//        this.price = price;
//        this.promotion = promotion;
//        this.image = image;
//    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
