package com.example.freshfoodapp.API;

import com.example.freshfoodapp.Models.Category;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.ProductQuantity;
import com.example.freshfoodapp.Models.ResponseObject;
import com.example.freshfoodapp.Models.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {

//    @FormUrlEncoded

    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseObject> login(@Field("username") String username, @Field("password") String password);

    @GET("category/parent")
    Call<List<Category>> getAll();

    @POST("sell/checkout/{user_id}")
    Call<ResponseObject> checkout(@Path("user_id") Long id, @Body ProductQuantity productQuantity);

    @FormUrlEncoded
    @POST("user/new")
    Call<ResponseObject> signup(@Field("username") String username, @Field("password") String password,
                                @Field("name") String name, @Field("email") String email);


    @Multipart
    @POST("upload_images/fileupload/user")
    Call<User> uploadImage(@Part("id") RequestBody id, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @PUT("user/changePassword")
    Call<ResponseObject> changePassword(@Field("id") Long id, @Field("newPass") String newpassword);

    @GET("user/{id}")
    Call<User> getUser(@Path("id") Long id);

    @FormUrlEncoded
    @PUT("user/new")
    Call<ResponseObject> changeProfile(@Field("id") Long id, @Field("name") String name, @Field("email") String email);

}
