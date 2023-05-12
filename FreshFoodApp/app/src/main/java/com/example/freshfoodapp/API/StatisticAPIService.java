package com.example.freshfoodapp.API;

import com.example.freshfoodapp.Models.Orders;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.ProductQuantity;
import com.example.freshfoodapp.Models.User;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatisticAPIService {
    @GET("order/revenue/{month}")
    Call<Double> getRevenueMonth(@Path("month") int month);

    @GET("order/revenue")
    Call<Double> getRevenue();


    @GET("order/revenue/order/{month}")
    Call<List<Orders>> getOrderMonth(@Path("month") int month);


    @GET("order/revenue/product/{month}")
    Call<List<Product>> getOrderProductSold(@Path("month") int month);
}
