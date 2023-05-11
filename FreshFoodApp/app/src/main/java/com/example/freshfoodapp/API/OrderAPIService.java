package com.example.freshfoodapp.API;

import com.example.freshfoodapp.Models.OrderItem;
import com.example.freshfoodapp.Models.OrderStatus;
import com.example.freshfoodapp.Models.Orders;
import com.example.freshfoodapp.Models.ResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderAPIService {
    @GET("order/{user_id}/{status}")
    Call<List<Orders>> getOrder(@Path("user_id") Long userId, @Path("status") int status);
    @GET("order/{orderId}")
    Call<List<OrderItem>> getOrderItem(@Path("orderId") Long orderId);

    @PUT("order/new/{status}/{orderId}")
    Call<Orders> updateStatus(@Path("status") int status, @Path("orderId") Long orderId);

}
