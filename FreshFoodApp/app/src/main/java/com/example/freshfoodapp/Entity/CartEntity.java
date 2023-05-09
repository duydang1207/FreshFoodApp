package com.example.freshfoodapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.freshfoodapp.Models.Product;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(tableName = "cart")
public class CartEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Long userId;
    private Long productId;
    private String name;
    private int quantity;
    private double price;
    private int promotion;
    private String image;

//    public CartEntity(int id, Long userId, Long productId, String name, int quantity, BigDecimal price, int promotion, String image) {
//        this.id = id;
//        this.userId = userId;
//        this.productId = productId;
//        this.name = name;
//        this.quantity = quantity;
//        this.price = price;
//        this.promotion = promotion;
//        this.image = image;
//    }

    public CartEntity() {
    }

    public int getId() {
        return id;
    }

    public void setProductCart(Product product){
        setPrice(product.getPrice().doubleValue());
        setImage(product.getImage());
        setProductId(product.getId());
        setPromotion(product.getPromotion());
        setName(product.getName());
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
