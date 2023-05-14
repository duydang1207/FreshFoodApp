package com.example.freshfoodapp.API;

import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.ProductQuantity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductAPIService {
    @GET("product/sold")
    Call<List<Product>> getProductSold();


    @GET("product/{id}")
    Call<Product> getProduct(@Path("id") Long productId);

    @GET("product/category/{category_id}")
    Call<List<Product>> getAllProductToCategory(@Path("category_id") Long category_id);

    @GET("product")
    Call<List<Product>> getAll();


}
