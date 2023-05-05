package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.freshfoodapp.Adapter.CartAdapter;
import com.example.freshfoodapp.Adapter.CartAdapterEntity;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Models.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCart;
    public static List<CartEntity> carts;
    public static TextView itemTotalPrice;
    Button btnPlus;
    Button btnMinus;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        Mapping();

        carts = AbstractDatabase.getInstance(getApplicationContext()).cartDAO().getAll();
        CartAdapterEntity adapter = new CartAdapterEntity(getApplicationContext(), carts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        TotalPrice();
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);





//        //Tạo Adapter
//        cartAdapter = new CartAdapter(CartActivity.this, R.layout.row_cart, carts);
//        //truyền dữ liệu từ adapter ra listview
//        listView.setAdapter(cartAdapter);


    }
    public static void TotalPrice(){
        BigDecimal total = BigDecimal.valueOf(0);
        int quantity = 0;
        for(int i =0;i<carts.size();i++)
        {
            quantity += carts.get(i).getQuantity();
            total = total.add(BigDecimal.valueOf(carts.get(i).getPrice()).multiply(BigDecimal.valueOf(quantity)));
        }
        itemTotalPrice.setText(String.valueOf("$ " + total));
    }
    private void Mapping() {
        rvCart = (RecyclerView) findViewById(R.id.lv_cart_products);
        itemTotalPrice = (TextView) findViewById(R.id.tv_cart_totalPrice);
        btnMinus = findViewById(R.id.btn_cartItem_minus);
        btnPlus = findViewById(R.id.btn_cartItem_plus);


        //Thêm dữ liệu vào List
//        carts = new List<CartEntity>();
//        carts.add(new Cart(1,"Cá basa tươi sống dfak fdsfsad  fsdfdsa ffdsafsda",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
//        carts.add(new Cart(2,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),7,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
//        carts.add(new Cart(3,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
//        carts.add(new Cart(4,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),10,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
//        carts.add(new Cart(5,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
//        carts.add(new Cart(5,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
    }
}