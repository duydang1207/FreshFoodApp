package com.example.freshfoodapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfoodapp.API.ProductAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Adapter.ProductSoldCategoryAdapter;
import com.example.freshfoodapp.Models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProductActivity extends AppCompatActivity {
    RecyclerView rvProducts;
    List<Product> products = new ArrayList<>();
    ProductAPIService productAPIService = RetrofitClient.getRetrofit().create(ProductAPIService.class);
    ProductSoldCategoryAdapter productAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        rvProducts = findViewById(R.id.rv_products_productall);
        getAllProduct();

    }
    void getAllProduct(){
        productAPIService.getAll().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                productAdapter = new ProductSoldCategoryAdapter(getApplicationContext(), products);
                GridLayoutManager gridLayoutManager =
                        new GridLayoutManager(getApplicationContext(),3);
                rvProducts.setLayoutManager(gridLayoutManager);
                rvProducts.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
