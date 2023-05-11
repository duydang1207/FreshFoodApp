package com.example.freshfoodapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.freshfoodapp.FreshPanel.Order_Fragment;
import com.google.android.material.tabs.TabLayout;


public class OrderListActivity extends AppCompatActivity {
    ImageView iv_orderList_back;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    ViewPageAdapter viewPageAdapter;
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        iv_orderList_back = findViewById(R.id.iv_orderList_back);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_page);
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

//      đưa về tablayout cần vào, hiện tại
        int location_tablayout = getIntent().getIntExtra("fragment", 0);
        mTabLayout.getTabAt(location_tablayout).select();

        iv_orderList_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
                startActivity(intent);
            }
        });


    }
    public class ViewPageAdapter extends FragmentStatePagerAdapter {

        public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Order_Fragment(position);
                case 1:
                    return new Order_Fragment(position);
                case 2:
                    return new Order_Fragment(position);
                case 3:
                    return new Order_Fragment(position);
                default:
                    return new Order_Fragment(0);
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        public CharSequence getPageTitle(int position)
        {
            String title ="";
            switch (position)
            {
                case 0:
                    title = "Chờ Xác nhận";
                    break;
                case 1:
                    title = "Đang giao";
                    break;
                case 2:
                    title ="Đã nhận";
                    break;
                case 3:
                    title ="Đã hủy";
                    break;

            }
            return title;
        }

    }

}
