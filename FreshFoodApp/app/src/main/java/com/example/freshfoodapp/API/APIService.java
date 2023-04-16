package com.example.freshfoodapp.API;

import androidx.room.Query;

import com.example.freshfoodapp.Models.Category;
import com.example.freshfoodapp.Models.Login;
import com.example.freshfoodapp.Models.ResponseObject;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

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
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIService {

//    @FormUrlEncoded

    @POST("/user/login")
    Call<ResponseObject> login(@Body Login login);

    @GET("/category/parent")
    Call<List<Category>> getAll();
}
