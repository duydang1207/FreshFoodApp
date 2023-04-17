package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private ImageView btnBackLogin;
    private TextView alreadyAccount;
    EditText username, name, email, password, confirmPassword;
    ConstraintLayout btnCreateAccout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



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
}