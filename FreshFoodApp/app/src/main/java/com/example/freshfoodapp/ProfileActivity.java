package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.API.APIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Models.ResponseObject;

import com.example.freshfoodapp.FreshPanel.FreshUserFragment;
import com.example.freshfoodapp.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ImageView imgAvatar, btn_back, btnChangePass, btnEdit, btnChange,btnLogout;

    FreshUserFragment userFragment = new FreshUserFragment();

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
        btnChange.setEnabled(false);
        btnChange.layout(0,0,0,0);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setEnabled(true);
                email.setEnabled(true);
                btnChange.setEnabled(true);
                btnEdit.setEnabled(false);
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long id = SharedPrefManager.getInstance(getApplicationContext()).getUser().getId();
                String nameNew = name.getText().toString();
                String emailNew = email.getText().toString();
                apiService.changeProfile(id, nameNew, emailNew).enqueue(new Callback<ResponseObject>() {
                    @Override
                    public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                        if(response.isSuccessful()){
                            User usernew = SharedPrefManager.getInstance(getApplicationContext()).getUser();
                            usernew.setName(nameNew);
                            usernew.setEmail(emailNew);
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(usernew);

                            name.setEnabled(false);
                            email.setEnabled(false);
                            btnChange.setEnabled(false);
                            btnEdit.setEnabled(true);

                            Toast.makeText(getApplicationContext(),"Thay đổi thông tin thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Không thể thay đổi thông tin do email đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseObject> call, Throwable t) {

                    }
                });
            }
        });

        getUser();

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
//                startActivity(new Intent(ProfileActivity.this, BottomNavigationActivity.class));
                setContentView(R.layout.activity_bottom_navigation);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, userFragment).commit();

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
        imgAvatar = findViewById(R.id.iv_profile_avatar);
        btn_back = findViewById(R.id.btn_proflie_back);
        btnChangePass = findViewById(R.id.btn_profile_changepassword);
        btnEdit = findViewById(R.id.btn_profile_edit);
        btnChange = findViewById(R.id.btn_profile_change);
        btnLogout = findViewById(R.id.btn_profile_logout);

    }
}