package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfoodapp.API.CartAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Adapter.OrderAdapter;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Models.Orders;
import com.example.freshfoodapp.Models.ProductQuantity;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    RecyclerView rvOrder;
    OrderAdapter adapter;
    List<CartEntity> listCart;
    TextView totalPrice,totalPriceMain, btnPayment;
    EditText phone,address;
    ProductQuantity list;

    CartAPIService apiService = RetrofitClient.getRetrofit().create(CartAPIService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Mapping();

        listCart = AbstractDatabase.getInstance(getApplicationContext()).cartDAO().getAll();
        adapter = new OrderAdapter(getApplicationContext(),listCart);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvOrder.setLayoutManager(layoutManager);
        rvOrder.setAdapter(adapter);
        list = (ProductQuantity) getIntent().getSerializableExtra("list");
        BigDecimal totalPrice = TotalPrice();

        Long id= SharedPrefManager.getInstance(getApplicationContext()).getUser().getId();
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập số điện thoại",Toast.LENGTH_SHORT).show();
                } else if (address.getText().length()==0) {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập số địa chỉ",Toast.LENGTH_SHORT).show();
                }
                else{
                    Payment(list,id,totalPrice);
                }
            }
        });
    }

    public void showAlertDialog(Context context)  {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set Title and Message:
        builder.setTitle("Thông báo").setMessage("Bạn đã đặt hàng thành công");

        //
        builder.setCancelable(true);

        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AbstractDatabase.getInstance(getApplicationContext()).cartDAO().deleteAll();
                startActivity(new Intent(getApplicationContext(),BottomNavigationActivity.class));
            }
        });


        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }

    void Payment(ProductQuantity list, Long userId, BigDecimal totalPrice){
        apiService.payment(list,userId,String.valueOf(phone.getText()),String.valueOf(address.getText()),totalPrice)
                .enqueue(new Callback<Orders>() {
                    @Override
                    public void onResponse(Call<Orders> call, Response<Orders> response) {
                        showAlertDialog(OrderActivity.this);
                    }

                    @Override
                    public void onFailure(Call<Orders> call, Throwable t) {
                        Log.e("Trang thanh toan",t.getMessage());
                    }
                });
    }
    BigDecimal TotalPrice(){
        int total = 0;
        int quantity = 0;
        for(int i =0;i<listCart.size();i++)
        {
            quantity = listCart.get(i).getQuantity();
            total += (BigDecimal.valueOf(listCart.get(i).getPrice()).multiply(BigDecimal.valueOf(100-listCart.get(i).getPromotion()).divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(quantity))).intValue();
        }
        totalPrice.setText(String.valueOf("đ" + total));
        totalPriceMain.setText(String.valueOf("đ" + total));

        return BigDecimal.valueOf(total);
    }

    void Mapping(){
        rvOrder = findViewById(R.id.rv_OrderItem);
        totalPrice = findViewById(R.id.tv_total_price_order);
        totalPriceMain = findViewById(R.id.tv_Order_TotalPriceMain);
        btnPayment = findViewById(R.id.btn_thanhtoan_order);
        phone = findViewById(R.id.edt_phone_order);
        address = findViewById(R.id.edt_address_order);
    }
}