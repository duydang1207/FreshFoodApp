package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = findViewById(R.id.image_product);
        Glide.
                with(getApplicationContext())
                .load("http://anhnendep.net/wp-content/uploads/2016/02/vit-boi-roi-Psyduck.jpg")
                .into(image);
        image.setScaleType(ShapeableImageView.ScaleType.FIT_XY);


    }
}