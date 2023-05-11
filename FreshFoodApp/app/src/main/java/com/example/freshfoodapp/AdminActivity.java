package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    private LinearLayout btnDoanhThu, btnDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Mapping();

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Bạn vừa nhấn vào Doanh thu", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(),ThongKeActivity.class);
                startActivity(intent);
            }
        });
        btnDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Đã vừa nhấn vào đơn hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Mapping(){
        btnDoanhThu = findViewById(R.id.btn_thongke);
        btnDonHang = findViewById(R.id.btn_donhang);

    }
}