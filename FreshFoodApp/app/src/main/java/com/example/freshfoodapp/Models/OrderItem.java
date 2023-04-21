package com.example.freshfoodapp.Models;

public class OrderItem {
    Long id;
    Orders orders;
    Inventory inventory;
    int quantity;

    public OrderItem(Long id, Orders orders, Inventory inventory, int quantity) {
        this.id = id;
        this.orders = orders;
        this.inventory = inventory;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
