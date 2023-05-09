package com.example.freshfoodapp.Models;

import java.io.Serializable;
import java.util.List;

public class ProductQuantity implements Serializable {
    List<Long> id;
    List<Integer> quantity;

    public ProductQuantity(List<Long> productId, List<Integer> quantity) {
        this.id = productId;
        this.quantity = quantity;
    }

    public List<Long> getId() {
        return id;
    }

    public void setId(List<Long> id) {
        this.id = id;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
