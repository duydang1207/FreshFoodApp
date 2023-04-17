package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freshfoodapp.API.APIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Models.Account;
import com.example.freshfoodapp.Models.Category;
import com.example.freshfoodapp.Models.ResponseObject;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    ConstraintLayout btn_loginGoogle, btnConfirm;

    EditText username;
    EditText password;

    APIService apiService;

    ResponseObject<Account> object;

    Account account;

    TextView signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mapping();

        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        btn_loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login(String.valueOf(username.getText()),String.valueOf(password.getText()));
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    void Mapping(){
        btnConfirm = findViewById(R.id.btn_login_signin);
        signUpBtn = findViewById(R.id.btn_login_signup);
        //Login with google
        btn_loginGoogle = findViewById(R.id.btn_login_google);
        username = findViewById(R.id.et_login_username);
        password = findViewById(R.id.et_login_password);
    }
    void SignIn(){
        Intent signinInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signinInIntent,1000);
    }

    void Login(String username, String password){
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.login(username,password).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                object = response.body();
                Gson gson = new Gson();
                account = gson.fromJson(object.getData(),Account.class);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    void navigateToSecondActivity(){
        finish();
        //chuyen man hinh sang main
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Login success!", Toast.LENGTH_SHORT).show();
        signUpBtn = findViewById(R.id.btn_login_signup);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }
}