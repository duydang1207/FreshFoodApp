package com.example.freshfoodapp.API;

import com.example.freshfoodapp.Models.OrderItem;
import com.example.freshfoodapp.Models.ResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartAPIService {

    @GET("sell/cart/{user_id}")
    Call<ResponseObject> getCart(@Path("user_id") Long userId);
}
