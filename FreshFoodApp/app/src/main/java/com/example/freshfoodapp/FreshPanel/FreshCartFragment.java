package com.example.freshfoodapp.FreshPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freshfoodapp.CartActivity;
import com.example.freshfoodapp.R;

public class FreshCartFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fresh_cart, null);
        getActivity().setTitle("Cart");

        startActivity(new Intent(getActivity(), CartActivity.class));
        return v;
    }
}
