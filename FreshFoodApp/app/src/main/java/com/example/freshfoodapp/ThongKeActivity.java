package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ThongKeActivity extends AppCompatActivity {

    private Spinner spnMonthDonHang, spnMonthDoanhThu, spnMonthSanPham, spnMonthHuy;
    private TextView tvDonHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        Mapping();

        List<String> listmonth = new ArrayList<>();
        for (int i=1; i<=12; i++){
            listmonth.add(String.valueOf(i));
        }

        ThongKeDonHang(listmonth);

        ThongKeSanPhamBanDuoc(listmonth);
        ThongKeDoanhThu(listmonth);
    }
    private void ThongKeDonHangBiHuy(List<String> listmonth){
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listmonth);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spnMonthHuy.setAdapter(adapter);
        spnMonthHuy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String monthClick = spnMonthHuy.getSelectedItem().toString();

                String slDonHang = "50";
                String tvDonHangTemp = "Số đơn hàng đã bị hủy " + monthClick + " là: " + slDonHang;
//                tvDonHang.setText((CharSequence) tvDonHang);
                Toast.makeText(ThongKeActivity.this, tvDonHangTemp, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void ThongKeSanPhamBanDuoc(List<String> listmonth){
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listmonth);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spnMonthSanPham.setAdapter(adapter);
        spnMonthSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String monthClick = spnMonthSanPham.getSelectedItem().toString();

                String slDonHang = "50";
                String tvDonHangTemp = "Tổng sản phẩm bán được của tháng " + monthClick + " là: " + slDonHang;
//                tvDonHang.setText((CharSequence) tvDonHang);
                Toast.makeText(ThongKeActivity.this, tvDonHangTemp, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void ThongKeDonHang(List<String> listmonth){
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listmonth);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        spnMonthDonHang.setAdapter(adapter);
        spnMonthDonHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String monthClick = spnMonthDonHang.getSelectedItem().toString();

                String slDonHang = "50";
                String tvDonHangTemp = "Tổng đơn hàng của tháng " + monthClick + " là: " + slDonHang;
//                tvDonHang.setText((CharSequence) tvDonHang);
                Toast.makeText(ThongKeActivity.this, tvDonHangTemp, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void ThongKeDoanhThu(List<String> listmonth){
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listmonth);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnMonthDoanhThu.setAdapter(adapter);
        spnMonthDoanhThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String monthClick = spnMonthDoanhThu.getSelectedItem().toString();

                String slDonHang = "50";
                String tvDonHangTemp = "Tổng doanh thu của tháng " + monthClick + " là: " + slDonHang;
//                tvDonHang.setText((CharSequence) tvDonHang);
                Toast.makeText(ThongKeActivity.this, tvDonHangTemp, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void Mapping(){
        tvDonHang = findViewById(R.id.tv_donhang);
        spnMonthDonHang = (Spinner) findViewById(R.id.spnMonthDonHang);
        spnMonthDoanhThu = (Spinner) findViewById(R.id.spnMonthDoanhThu);
        spnMonthSanPham = (Spinner) findViewById(R.id.spnMonthSanPham);
        spnMonthHuy = (Spinner) findViewById(R.id.spnMonthHuy);
    }
}