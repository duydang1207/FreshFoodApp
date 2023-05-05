package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.API.ProductAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Models.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    ConstraintLayout btnAddToCart;
    TextView proName, proPrice, proDesc;
    ImageView proImg;
    ProductAPIService productAPIService;

    Product product;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Mapping();
        getProductDetail(getIntent().getLongExtra("id",0));

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
    void getProductDetail(Long id){
        productAPIService = RetrofitClient.getRetrofit().create(ProductAPIService.class);
        productAPIService.getProduct(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                product = response.body();

                proName.setText(String.valueOf(product.getName()));
                proDesc.setText(String.valueOf(product.getDescription()));
                proPrice.setText(String.valueOf(product.getPrice()));
                Glide.with(context).load(product.getImage()).into(proImg);

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
    void Mapping(){
        btnAddToCart = (ConstraintLayout) findViewById(R.id.btn_prodt_addcart);
        proName = (TextView) findViewById(R.id.tv_prodt_namepro);
        proPrice = (TextView) findViewById(R.id.tv_prodt_pricepro);
        proImg = (ImageView) findViewById(R.id.ima_prodt_imgpro);
        proDesc = (TextView) findViewById(R.id.tv_prodt_descrpro);
    }
}