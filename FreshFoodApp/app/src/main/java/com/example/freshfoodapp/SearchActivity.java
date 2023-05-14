package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.freshfoodapp.API.ProductAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Adapter.ProductSoldCategoryAdapter;
import com.example.freshfoodapp.Adapter.SearchAdapter;
import com.example.freshfoodapp.Models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    RecyclerView rvProduct;
    EditText editText;

    List<Product> productList;
    SearchAdapter productAdapter;

    ProductAPIService productAPIService = RetrofitClient.getRetrofit().create(ProductAPIService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvProduct = (RecyclerView) findViewById(R.id.searchview);
        editText = (EditText) findViewById(R.id.search_input_homepage);
        initList();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SearchActivity.this.productAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void initList() {
        productAPIService.getAll().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                productAdapter = new SearchAdapter(getApplicationContext(), productList);
                GridLayoutManager gridLayoutManager =
                        new GridLayoutManager(getApplicationContext(),1);
                rvProduct.setLayoutManager(gridLayoutManager);
                rvProduct.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }
}