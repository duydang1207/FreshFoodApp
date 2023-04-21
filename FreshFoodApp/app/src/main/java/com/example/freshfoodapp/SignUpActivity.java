package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfoodapp.API.APIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Models.Account;
import com.example.freshfoodapp.Models.ResponseObject;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ImageView btnBackLogin;
    private TextView alreadyAccount;
    EditText username, name, email, password, confirmPassword;
    ConstraintLayout btnCreateAccout;

    APIService apiService;
    Object object;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Mapping();

        //button back
        btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
        //button login
        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

        //button create account
        btnCreateAccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp(String.valueOf(username.getText()), String.valueOf(password.getText()),
                        String.valueOf(name.getText()), String.valueOf(email.getText()));
            }
        });


    }
    void Mapping(){
        btnBackLogin = findViewById(R.id.btn_back_login);
        alreadyAccount = findViewById(R.id.tv_login_already);
        name = findViewById(R.id.name_input_signup);
        username = findViewById(R.id.user_input_signup);
        email = findViewById(R.id.email_input_signup);
        password = findViewById(R.id.password_input_signup);
        confirmPassword = findViewById(R.id.confirm_password_input_signup);
        btnCreateAccout = findViewById(R.id.btnSignUp);
    }
    void SignUp(String username, String password, String name, String email){
        //kiem tra password
        if(!password.equals(String.valueOf(confirmPassword.getText()))){
            Toast.makeText(this, "Mật khẩu không trùng khớp! Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 8) {
            Toast.makeText(this, "Mật khẩu có tối thiểu 8 kí tự!", Toast.LENGTH_SHORT).show();
        } else{
            apiService = RetrofitClient.getRetrofit().create(APIService.class);
            apiService.signup(username, password, name, email).enqueue(new Callback<ResponseObject>() {
                @Override
                public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                    if(response.isSuccessful()) {
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Tài khoản hoặc email đã tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseObject> call, Throwable t) {
                    Log.e("Error",t.getMessage());
                }
            });
        }

    }
}