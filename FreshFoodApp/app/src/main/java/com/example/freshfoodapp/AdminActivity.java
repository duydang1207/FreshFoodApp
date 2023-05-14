package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    private LinearLayout btnDoanhThu, btnDonHang;
    ImageView btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Mapping();

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Bạn vừa nhấn vào Doanh thu", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(getApplicationContext(),ThongKeActivity2.class);
                startActivity(intent);
            }
        });
        btnDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), OrderListAdminActivity.class);
                intent.putExtra("fragment", 0);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
    }
    private void Mapping(){
        btnDoanhThu = findViewById(R.id.btn_thongke);
        btnDonHang = findViewById(R.id.btn_donhang);
        btnLogout = findViewById(R.id.btn_logout_admin);


    }
}