package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.freshfoodapp.Adapter.CartAdapter;
import com.example.freshfoodapp.Models.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Cart> carts;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        Mapping();
        //Tạo Adapter
        cartAdapter = new CartAdapter(CartActivity.this, R.layout.row_cart, carts);
        //truyền dữ liệu từ adapter ra listview
        listView.setAdapter(cartAdapter);


    }
    private void Mapping() {
        listView = (ListView) findViewById(R.id.lv_cart_products);

        //Thêm dữ liệu vào List
        carts = new ArrayList<>();
        carts.add(new Cart(1,"Cá basa tươi sống dfak fdsfsad  fsdfdsa ffdsafsda",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(2,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),7,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(3,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(4,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),10,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(5,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(5,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
    }
}