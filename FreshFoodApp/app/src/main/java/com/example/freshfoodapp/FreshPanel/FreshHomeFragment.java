package com.example.freshfoodapp.FreshPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Adapter.CategoryAdapter;
import com.example.freshfoodapp.Adapter.ProductAdapter;
import com.example.freshfoodapp.Domain.CategoryDomain;
import com.example.freshfoodapp.Domain.ProductDomain;
import com.example.freshfoodapp.HomePageActivity;
import com.example.freshfoodapp.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;

import java.util.ArrayList;

public class FreshHomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private RecyclerView rvCateList, rvProTrend;
    private RecyclerView.Adapter adapter;
    private View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.activity_home_page, container, false);

        recycleViewCategory();
        recycleViewTrending();
        viewFlipper = v.findViewById(R.id.vf_homepage_imgFlipper);

        viewFiliper();
        return v;
    }
    public void recycleViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvCateList = v.findViewById(R.id.rv_homepage_cate);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvProTrend = v.findViewById(R.id.rv_homepage_protrend);
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
    public void viewFiliper(){

        ArrayList<String> listAd = new ArrayList<>();

        listAd.add("http://freshfoods.vn/images/cover-freshfoods.png");

        listAd.add("http://freshfoods.vn/images/cover-beef.jpg");

        listAd.add("http://freshfoods.vn/images/cover-salmon-freshfoods.jpg");
        listAd.add("https://vavista.com/wp-content/uploads/2017/05/AdobeStock_111774771.jpg");



        for (int i=0; i < listAd.size(); i++){

            ShapeableImageView shapeableImageView = new ShapeableImageView(getActivity().getApplicationContext());

            shapeableImageView.setShapeAppearanceModel(shapeableImageView.getShapeAppearanceModel()
                    .toBuilder()
                    .setAllCorners(CornerFamily.ROUNDED, 30)
                    .build());

            Glide.with(getActivity().getApplicationContext()).load(listAd.get(i)).into(shapeableImageView);

            shapeableImageView.setScaleType(ShapeableImageView.ScaleType.FIT_XY); // Chỉnh kích thước vừa đủ viewFlipper

            viewFlipper.addView(shapeableImageView);

        }

        viewFlipper.setFlipInterval(4000); // Thời gian thay đổi, giống như delay vậy

        viewFlipper.setAutoStart(true);		// Tự động chạy khi mở màn hình



        Animation slidein = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_in_right);

        Animation slideout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_out_left);

        viewFlipper.setInAnimation(slidein);

        viewFlipper.setOutAnimation(slideout);
    }
}
