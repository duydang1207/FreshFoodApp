package com.example.freshfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class ProductOfCategoryActivity extends AppCompatActivity {

    TextView categoryName;
    RecyclerView rvProducts;
    List<Product> products = new ArrayList<>();
    public ProductAPIService productAPIService;
    ProductSoldCategoryAdapter productAdapter;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        productAPIService = RetrofitClient.getRetrofit().create(ProductAPIService.class);
        getProductToCategoryId();

        btnBack = (ImageView) findViewById(R.id.iv_cart_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), BottomNavigationActivity.class));
            }
        });

    }
    void getProductToCategoryId(){
        rvProducts = findViewById(R.id.rv_products_productall);
        categoryName = findViewById(R.id.tv_products_categoryname);

        String category = getIntent().getStringExtra("catename");
        categoryName.setText(category.toString());

        if(category.equals("Thịt")){
            getProduct(1L, 2L, 3L);
        } else if (category.equals("Cá")) {
            getProduct(4L);
        } else if (category.equals("Trứng")) {
            getProduct(6L);
        } else if (category.equals("Rau củ quả")) {
           getProduct(7L, 8L, 9L);
        }
        else {
            getProduct(5L);
        }
    }

    void getProduct(Long id1, Long id2, Long id3){
        productAPIService.getAllProductToCategory(id1).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                productAPIService.getAllProductToCategory(id2).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        products.addAll(response.body());
                        productAPIService.getAllProductToCategory(id3).enqueue(new Callback<List<Product>>() {
                            @Override
                            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                products.addAll(response.body());
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
                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                    }
                });


            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
    void getProduct(Long id){
        productAPIService.getAllProductToCategory(id).enqueue(new Callback<List<Product>>() {
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
