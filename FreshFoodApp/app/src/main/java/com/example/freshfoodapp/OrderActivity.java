package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfoodapp.Adapter.OrderAdapter;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView rvOrder;
    OrderAdapter adapter;
    List<CartEntity> listCart;
    TextView totalPrice,totalPriceMain, btnPayment;
    EditText phone,address;
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
        TotalPrice();

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phone.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập số điện thoại",Toast.LENGTH_SHORT).show();
                } else if (address.getText().length()==0) {
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập số địa chỉ",Toast.LENGTH_SHORT).show();
                }
                else{
                    Payment();
                }
            }
        });
    }
    void Payment(){

    }
    void TotalPrice(){
        BigDecimal total = BigDecimal.valueOf(0);
        int quantity = 0;
        for(int i =0;i<listCart.size();i++)
        {
            quantity += listCart.get(i).getQuantity();
            total = total.add(BigDecimal.valueOf(listCart.get(i).getPrice()).multiply(BigDecimal.valueOf(quantity)));
        }
        totalPrice.setText(String.valueOf("đ" + total));
        totalPriceMain.setText(String.valueOf("đ" + total));
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