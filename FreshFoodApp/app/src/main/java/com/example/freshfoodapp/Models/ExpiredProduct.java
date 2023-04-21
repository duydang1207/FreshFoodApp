package com.example.freshfoodapp.Models;

public class ExpiredProduct {
    int id;

    Inventory inventory;

    public ExpiredProduct(int id, Inventory inventory) {
        this.id = id;
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
