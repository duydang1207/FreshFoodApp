package com.example.freshfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.freshfoodapp.FreshPanel.FreshHomeFragment;
import com.example.freshfoodapp.FreshPanel.FreshLoveFragment;
import com.example.freshfoodapp.FreshPanel.FreshNoticeFragment;
import com.example.freshfoodapp.FreshPanel.FreshUserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.freshfood_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        //startActivity(new Intent(this,HomePageActivity.class));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.freshHome:
                fragment = new FreshHomeFragment();
                break;
            case R.id.freshProduct:
                fragment = new FreshLoveFragment();
                break;
            case R.id.freshCart:
                fragment = new FreshNoticeFragment();
                break;
            case R.id.freshUser:
                fragment = new FreshUserFragment();
                break;
        }
        return loadFreshFragment(fragment);
    }

    private boolean loadFreshFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }
}