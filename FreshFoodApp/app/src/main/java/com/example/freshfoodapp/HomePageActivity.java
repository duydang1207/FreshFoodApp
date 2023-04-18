package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfoodapp.Adapter.CategoryAdapter;
import com.example.freshfoodapp.Adapter.ProductAdapter;
import com.example.freshfoodapp.Domain.CategoryDomain;
import com.example.freshfoodapp.Domain.ProductDomain;
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
    private RecyclerView rvCateList, rvProTrend;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        viewFlipper = findViewById(R.id.vf_homepage_imgFlipper);

        viewFiliper();
        recycleViewCategory();
        recycleViewTrending();
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
    public void recycleViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCateList = findViewById(R.id.rv_homepage_cate);
        rvCateList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> cates = new ArrayList<>();
        cates.add(new CategoryDomain("Thịt","meat"));
        cates.add(new CategoryDomain("Cá","fish"));
        cates.add(new CategoryDomain("Trứng","eggs"));
        cates.add(new CategoryDomain("Rau củ quả","vegetables"));
        cates.add(new CategoryDomain("Hải sản","seafood"));

        adapter = new CategoryAdapter(cates);
        rvCateList.setAdapter(adapter);
    }

    public void recycleViewTrending(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvProTrend = findViewById(R.id.rv_homepage_protrend);
        rvProTrend.setLayoutManager(linearLayoutManager);

        ArrayList<ProductDomain> proTrends = new ArrayList<>();
        proTrends.add(new ProductDomain("Nạc Dăm Heo","meat_pig",47500.0,1));
        proTrends.add(new ProductDomain("Bắp bò Mỹ","meat_beef", 120000.0,2));
        proTrends.add(new ProductDomain("Cá ngừ bò nguyên con","fish_ngu", 130000.0,3));
        proTrends.add(new ProductDomain("Tôm càng xanh","tom", 95000.0,4));
        proTrends.add(new ProductDomain("Bắp bò Mỹ","meat_beef", 120000.0,5));

        adapter = new ProductAdapter(proTrends);
        rvProTrend.setAdapter(adapter);
    }
}