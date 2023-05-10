package com.example.freshfoodapp.FreshPanel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.LoginActivity;
import com.example.freshfoodapp.Models.User;
import com.example.freshfoodapp.ProfileActivity;
import com.example.freshfoodapp.R;
import com.example.freshfoodapp.SharedPrefManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class FreshUserFragment extends Fragment {
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    private LinearLayout btn_profile;
    TextView txtName;
    private View v;
    FreshUserFragment context=this;
    LinearLayout btnLogout;
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
        getUser();

        //logut with google
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this.getContext(), googleSignInOptions);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbstractDatabase.getInstance(getContext()).cartDAO().deleteAll();
                logoutWithGoogle();

            }
        });

        return v;
    }
    void getUser(){
        User user = SharedPrefManager.getInstance(context.getContext()).getUser();
        txtName.setText(user.getName().toString());
    }
    private void Mapping(){
        btn_profile = v.findViewById(R.id.btn_profile_user);
        txtName = v.findViewById(R.id.fr_user_name);
        btnLogout = v.findViewById(R.id.btn_freuser_logout);
    }
    void logoutWithGoogle(){
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        SharedPrefManager.getInstance(this.getContext()).logout();
    }
}
