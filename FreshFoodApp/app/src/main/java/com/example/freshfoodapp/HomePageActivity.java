package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.Shapeable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        viewFlipper = findViewById(R.id.vf_homepage_imgFlipper);

        viewFiliper();
    }

    public void viewFiliper(){

        ArrayList<String> listAd = new ArrayList<>();

        listAd.add("http://freshfoods.vn/images/cover-freshfoods.png");

        listAd.add("http://freshfoods.vn/images/cover-beef.jpg");

        listAd.add("http://freshfoods.vn/images/cover-salmon-freshfoods.jpg");
        listAd.add("https://vavista.com/wp-content/uploads/2017/05/AdobeStock_111774771.jpg");



        for (int i=0; i < listAd.size(); i++){

            ShapeableImageView shapeableImageView = new ShapeableImageView(getApplicationContext());

            shapeableImageView.setShapeAppearanceModel(shapeableImageView.getShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED, 30)
                    .build());

            Glide.with(getApplicationContext()).load(listAd.get(i)).into(shapeableImageView);

            shapeableImageView.setScaleType(ShapeableImageView.ScaleType.FIT_XY); // Chỉnh kích thước vừa đủ viewFlipper

            viewFlipper.addView(shapeableImageView);

        }

        viewFlipper.setFlipInterval(4000); // Thời gian thay đổi, giống như delay vậy

        viewFlipper.setAutoStart(true);		// Tự động chạy khi mở màn hình



        Animation slidein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);

        Animation slideout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);

        viewFlipper.setInAnimation(slidein);

        viewFlipper.setOutAnimation(slideout);
    }
}