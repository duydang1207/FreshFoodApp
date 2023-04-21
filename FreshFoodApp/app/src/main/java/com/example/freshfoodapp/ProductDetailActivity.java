package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends AppCompatActivity {

    TextView btn_Plus, btn_Minus, quantity;
    ImageView btn_AddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Mapping();

        btn_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_temp = Integer.parseInt(quantity.getText().toString()) + 1;
                quantity.setText(String.valueOf(quantity_temp));
            }
        });
        btn_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_temp = Integer.parseInt(String.valueOf(quantity.getText().toString()));

                if(quantity_temp > 1){
                    quantity_temp--;
                    quantity.setText(String.valueOf(quantity_temp));
                }
                else
                    Toast.makeText(getApplicationContext(),"Đã đạt số lượng tối thiểu",Toast.LENGTH_SHORT).show();
            }
        });


    }
    void Mapping(){
        btn_Minus = findViewById(R.id.btn_cart_minus);
        btn_Plus = findViewById(R.id.btn_cart_plus);
        btn_AddToCart = findViewById(R.id.btn_productdetail_addtocart);
        quantity = findViewById(R.id.tv_cart_quantitybuy);
    }
}