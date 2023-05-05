package com.example.freshfoodapp.FreshPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freshfoodapp.ProfileActivity;
import com.example.freshfoodapp.R;

public class FreshUserFragment extends Fragment {
    private LinearLayout btn_profile;
    private View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fresh_user, null);
        Mapping();
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });
        return v;
    }
    private void Mapping(){
        btn_profile = v.findViewById(R.id.btn_profile_user);
    }
}
