package com.example.freshfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.freshfoodapp.API.APIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    Button btnLogout, btnChangePass;
    ImageView imgAvatar, btn_back;

    EditText name, email;
    Context context = this;
    APIService apiService = RetrofitClient.getRetrofit().create(APIService.class);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Mapping();
        //block edit text
        name.setEnabled(false);
        email.setEnabled(false);

        getUser();


        //click avatar
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, UploadAvatarActivity.class));
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, UploadAvatarActivity.class));
            }
        });
        //click back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kiem tra user duoc tao băng google
                User user = SharedPrefManager.getInstance(context).getUser();
                Long userid = user.getId();
                apiService.getUser(userid).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body().getPassword().equals("")){
                            Toast.makeText(context, "Không thể đổi mật khẩu do tài khoản đăng kí bằng google", Toast.LENGTH_SHORT).show();
                        }else {
                            startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

            }
        });

    }
    void getUser(){
        User user = SharedPrefManager.getInstance(context).getUser();
        name.setText(user.getName());
        email.setText(user.getEmail());
        Glide.with(context).load(user.getAvatar()).into(imgAvatar);
    }
    void Mapping(){
        name = findViewById(R.id.et_profile_name);
        email = findViewById(R.id.et_proflie_email);
        btnLogout = findViewById(R.id.btn_profile_logout);
        imgAvatar = findViewById(R.id.iv_profile_avatar);
        btn_back = findViewById(R.id.btn_proflie_back);
        btnChangePass = findViewById(R.id.btn_profile_changepassword);

    }
}