package com.example.freshfoodapp.API;

import com.example.freshfoodapp.Models.ResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderAPIService {
    @GET("order/{user_id}/{status}")
    Call<ResponseObject> getOrder(@Path("user_id") Long userId, @Path("status") int status);
}
