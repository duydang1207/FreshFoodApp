package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;

public class ProductDetailActivity extends AppCompatActivity {

    ConstraintLayout btnAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Mapping();
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartEntity cart = new CartEntity("Cá basa",2,20000,5,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg");
                AbstractDatabase.getInstance(getApplicationContext()).cartDAO().insert(cart);
                Toast.makeText(getApplicationContext(),"Thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
            }
        });


    }
    void Mapping(){
        btnAddToCart = (ConstraintLayout) findViewById(R.id.btn_prodt_addcart);
    }
}