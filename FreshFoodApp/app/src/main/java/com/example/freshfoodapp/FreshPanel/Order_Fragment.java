package com.example.freshfoodapp.FreshPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.freshfoodapp.API.OrderAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Adapter.OrderStatusAdapter;
import com.example.freshfoodapp.Models.Orders;
import com.example.freshfoodapp.Models.User;
import com.example.freshfoodapp.R;
import com.example.freshfoodapp.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_Fragment extends Fragment {
    private final int position;
    List<Orders> listOrder = new ArrayList<>();
    OrderAPIService orderAPIService = RetrofitClient.getRetrofit().create(OrderAPIService.class);

    public Order_Fragment(int position) {
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_order, container, false);
        ListView listViewOrder = view.findViewById(R.id.listorder_confirm);


        User user = SharedPrefManager.getInstance(getContext()).getUser();
        orderAPIService.getOrder(user.getId(),position).enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {
                listOrder = response.body();
                OrderStatusAdapter orderListViewAdapter = new OrderStatusAdapter(requireContext() , R.layout.order_id, listOrder);
                listViewOrder.setAdapter(orderListViewAdapter);
                if(!listOrder.isEmpty())
                {

                    LinearLayout checkorder = view.findViewById(R.id.empty_order);
                    checkorder.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {
            }
        });
        return view;
    }
}