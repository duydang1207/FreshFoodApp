package com.example.freshfoodapp.API;

import com.example.freshfoodapp.Models.Orders;
import com.example.freshfoodapp.Models.ProductQuantity;
import com.example.freshfoodapp.Models.ResponseObject;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartAPIService {

    @GET("sell/cart/{user_id}")
    Call<ResponseObject> getCart(@Path("user_id") Long userId);

    @POST("sell/check")
    Call<ResponseObject> checkProduct(@Body ProductQuantity productQuantity);

    @POST("sell/payment/{user_id}/{phone}/{address}/{totalPrice}")
    Call<Orders> payment(@Body ProductQuantity list,
                         @Path("user_id") Long userId, @Path("phone") String phone,
                         @Path("address") String address, @Path("totalPrice")BigDecimal totalPrice);
}
