package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.freshfoodapp.API.ProductAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.API.StatisticAPIService;
import com.example.freshfoodapp.Adapter.ThongKe.RevenuAdapter;
import com.example.freshfoodapp.Adapter.ThongKe.RevenuOrderAdapter;
import com.example.freshfoodapp.Adapter.ThongKe.RevenuProductAdapter;
import com.example.freshfoodapp.Models.Orders;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.ProductQuantity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKeActivity2 extends AppCompatActivity {

    Spinner spnMonth ,spnType;
    RecyclerView rvThongKe;

    StatisticAPIService apiService = RetrofitClient.getRetrofit().create(StatisticAPIService.class);

    List<Product> products;

    Double total;

    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke2);
        Mapping();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongKeActivity2.this,AdminActivity.class));
            }
        });
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String option=spnType.getSelectedItem().toString();
                if(option.compareTo("Đơn hàng")==0){
                    getOrder(spnMonth.getSelectedItem().toString());
                }else if(option.compareTo("Sản phẩm")==0)
                {
                    products = new ArrayList<>();
                    getProduct(spnMonth.getSelectedItem().toString());
                }
                else{getRevenu(spnMonth.getSelectedItem().toString());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String option=spnType.getSelectedItem().toString();
                if(option.compareTo("Đơn hàng")==0){
                    getOrder(spnMonth.getSelectedItem().toString());
                }else if(option.compareTo("Sản phẩm")==0)
                {
                    products = new ArrayList<>();
                    getProduct(spnMonth.getSelectedItem().toString());
                }
                else{getRevenu(spnMonth.getSelectedItem().toString());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    void Mapping(){
        spnMonth = findViewById(R.id.spinner_month);
        spnType = findViewById(R.id.spinner_type);
        rvThongKe = findViewById(R.id.rv_ThongKe);
        back = findViewById(R.id.iv_thongke_back);

        getTotal();
        List<String> listmonth = new ArrayList<>();
        for (int i=1; i<=12; i++){
            listmonth.add(String.valueOf(i));
        }
        List<String> type = new ArrayList<>();
        type.add("Đơn hàng");
        type.add("Sản phẩm");
        type.add("Doanh thu");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listmonth);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnMonth.setAdapter(adapter);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,type);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnType.setAdapter(adapter);
    }
    void getTotal(){
        apiService.getRevenue().enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                total =response.body();
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {

            }
        });
    }
    void getRevenu(String month){
        apiService.getRevenueMonth(Integer.parseInt(month)).enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                Double  totalMonth = response.body();

                RevenuAdapter adapter = new RevenuAdapter(getApplicationContext(),total,totalMonth);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                rvThongKe.setLayoutManager(layoutManager);
                rvThongKe.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {

            }
        });
    }
    void getOrder(String month){
        apiService.getOrderMonth(Integer.parseInt(month)).enqueue(new Callback<List<Orders>>() {
            @Override
            public void onResponse(Call<List<Orders>> call, Response<List<Orders>> response) {
                List<Orders> orders = response.body();
                RevenuOrderAdapter adapter = new RevenuOrderAdapter(getApplicationContext(), orders);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                rvThongKe.setLayoutManager(layoutManager);
                rvThongKe.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Orders>> call, Throwable t) {

            }
        });
    }

    void getProduct(String month){
        apiService.getOrderProductSold(Integer.parseInt(month)).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                RevenuProductAdapter adapter = new RevenuProductAdapter(getApplicationContext(),products);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                rvThongKe.setLayoutManager(layoutManager);
                rvThongKe.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

    }



}




