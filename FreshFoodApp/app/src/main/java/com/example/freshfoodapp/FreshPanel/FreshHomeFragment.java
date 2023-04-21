package com.example.freshfoodapp.FreshPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfoodapp.Adapter.CategoryAdapter;
import com.example.freshfoodapp.Domain.CategoryDomain;
import com.example.freshfoodapp.HomePageActivity;
import com.example.freshfoodapp.R;

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
}
