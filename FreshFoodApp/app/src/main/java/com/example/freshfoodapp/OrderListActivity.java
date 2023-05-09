package com.example.freshfoodapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.freshfoodapp.FreshPanel.Cancelled_Order_Fragment;
import com.example.freshfoodapp.FreshPanel.Confirm_Order_Fragment;
import com.example.freshfoodapp.FreshPanel.Delivering_Order_Fragment;
import com.example.freshfoodapp.FreshPanel.Received_Order_Fragment;
import com.google.android.material.tabs.TabLayout;


public class OrderListActivity extends AppCompatActivity {


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        TabLayout mTabLayout = findViewById(R.id.tab_layout);
        ViewPager mViewPager = findViewById(R.id.view_page);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);




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
                    return new Confirm_Order_Fragment();
                case 1:
                    return new Delivering_Order_Fragment();
                case 2:
                    return new Received_Order_Fragment();
                case 3:
                    return new Cancelled_Order_Fragment();
                default:
                    return new Confirm_Order_Fragment();
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
