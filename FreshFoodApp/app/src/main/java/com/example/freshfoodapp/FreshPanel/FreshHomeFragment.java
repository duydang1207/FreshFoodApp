package com.example.freshfoodapp.FreshPanel;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshfoodapp.API.ProductAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Adapter.CategoryAdapter;
import com.example.freshfoodapp.Adapter.ProductSoldCategoryAdapter;
import com.example.freshfoodapp.Domain.CategoryDomain;
import com.example.freshfoodapp.GetProductActivity;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.User;
import com.example.freshfoodapp.R;
import com.example.freshfoodapp.SharedPrefManager;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreshHomeFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private RecyclerView rvCateList, rvProTrend;
    private RecyclerView.Adapter adapter;
    private View v;
    private ProductAPIService productAPIService;
    private List<Product> products;
    private ProductSoldCategoryAdapter productSoldAdapter;

    TextView name, btnCateMore, btnProductMore;
    CircleImageView img;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.activity_home_page, container, false);

        btnCateMore = v.findViewById(R.id.btn__homepage_catemore);
        btnProductMore = v.findViewById(R.id.btn_homepage_productmore);

        btnCateMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GetProductActivity.class));
            }
        });
        btnProductMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), GetProductActivity.class));
            }
        });

        recycleViewCategory();
        recycleViewTrending();
        viewFlipper = v.findViewById(R.id.vf_homepage_imgFlipper);

        viewFiliper();
        getUser();
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

        adapter = new CategoryAdapter(getContext() ,cates);
        rvCateList.setAdapter(adapter);
    }
    public void recycleViewTrending(){
        rvProTrend = v.findViewById(R.id.rv_homepage_protrend);
        productAPIService = RetrofitClient.getRetrofit().create(ProductAPIService.class);
        productAPIService.getProductSold().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                List<Product> productList = new ArrayList<>();
                for(int i=0; i<5; i++){
                    productList.add(products.get(i));
                }

                productSoldAdapter = new ProductSoldCategoryAdapter(getContext() ,productList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                rvProTrend.setLayoutManager(linearLayoutManager);
                rvProTrend.setAdapter(productSoldAdapter);
                productSoldAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
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
    void getUser(){
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        name= v.findViewById(R.id.tv_homepage_name);
        img = v.findViewById(R.id.iv_homepage_imgProfile);

        name.setText(user.getName());
        Glide.with(getActivity().getApplicationContext()).load(user.getAvatar()).into(img);
    }
}
