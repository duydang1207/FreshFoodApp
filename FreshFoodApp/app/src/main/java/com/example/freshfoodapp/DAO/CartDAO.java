package com.example.freshfoodapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.freshfoodapp.Entity.CartEntity;

import java.util.List;

@Dao
public interface CartDAO {
    @Query("Select * from Cart")
    List<CartEntity> getAll();

    @Query("Delete from cart")
    void deleteAll();

    @Query("Delete from cart where cart.productId=:id")
    void deleteProduct(Long id);

    @Insert
    void insert(CartEntity cart);

    @Update
    void update(CartEntity cart);

    @Delete
    void delete(CartEntity cart);
}

