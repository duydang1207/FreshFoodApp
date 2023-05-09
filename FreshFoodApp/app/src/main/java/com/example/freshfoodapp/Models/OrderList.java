package com.example.freshfoodapp.Models;

public class OrderList {
    private int order_id;
//    private String address;
//    private int complete;
//    private String payment_complete;
//
//    private  String phone;
//    private int status;
    private  float total_price;
//    private int user_id;

    private  String image_product;

    private String name_product;

    private float price_product;

    private  int quantity;

    public OrderList(int order_id, float total_price, String image_product, String name_product, float price_product, int quantity) {
        this.order_id = order_id;
        this.total_price = total_price;
        this.image_product = image_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getImage_product() {
        return image_product;
    }

    public void setImage_product(String image_product) {
        this.image_product = image_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public float getPrice_product() {
        return price_product;
    }

    public void setPrice_product(float price_product) {
        this.price_product = price_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
