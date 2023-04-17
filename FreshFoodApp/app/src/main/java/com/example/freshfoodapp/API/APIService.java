package com.example.freshfoodapp.API;

import com.example.freshfoodapp.Models.Category;
import com.example.freshfoodapp.Models.ResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

//    @FormUrlEncoded

    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseObject> login(@Field("username") String username, @Field("password") String password);

    @GET("category/parent")
    Call<List<Category>> getAll();
}
