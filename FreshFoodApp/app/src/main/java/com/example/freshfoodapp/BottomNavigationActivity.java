package com.example.freshfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.freshfoodapp.FreshPanel.FreshCartFragment;
import com.example.freshfoodapp.FreshPanel.FreshHomeFragment;
import com.example.freshfoodapp.FreshPanel.FreshNotificationFragment;
import com.example.freshfoodapp.FreshPanel.FreshLoveFragment;
import com.example.freshfoodapp.FreshPanel.FreshUserFragment;
import com.example.freshfoodapp.FreshPanel.ViewPagerAdapter;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavigationActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    FreshHomeFragment homeFragment = new FreshHomeFragment();
    FreshCartFragment cartFragment = new FreshCartFragment();
    FreshNotificationFragment notifyFragment = new FreshNotificationFragment();
    FreshUserFragment userFragment = new FreshUserFragment();
    FreshLoveFragment loveFragment = new FreshLoveFragment();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        BottomNavigationView bottomNavigationView = findViewById(R.id.freshfood_bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.freshCart);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(5);

        BadgeDrawable badgeDrawable2 = bottomNavigationView.getOrCreateBadge(R.id.freshNotification);
        badgeDrawable2.setVisible(true);
        badgeDrawable2.setNumber(10);

        //Thanh navigation sẽ ẩn khi lướt màn hình
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());

        //View pager
        viewPager = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //add fragment
        viewPagerAdapter.add(new FreshHomeFragment());
        viewPagerAdapter.add(new FreshLoveFragment());
        viewPagerAdapter.add(new FreshCartFragment());
        viewPagerAdapter.add(new FreshNotificationFragment());
        viewPagerAdapter.add(new FreshUserFragment());


        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.freshHome).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.freshLove).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.freshCart).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.freshNotification).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.freshUser).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.freshHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.freshLove:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, loveFragment).commit();
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.freshCart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, cartFragment).commit();
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.freshNotification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, notifyFragment).commit();
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.freshUser:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, userFragment).commit();
                        viewPager.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });
    }

}