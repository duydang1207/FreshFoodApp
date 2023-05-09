package com.example.freshfoodapp;

import static com.example.freshfoodapp.SharedPrefManager.SHARED_PREF_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfoodapp.API.APIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Models.ResponseObject;
import com.example.freshfoodapp.Models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    TextView textNewPass, textConfirm;
    Button btnSetPassword;
    Long userid;
    Context context = this;
    APIService apiService = RetrofitClient.getRetrofit().create(APIService.class);;
    ResponseObject object;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Mapping();
        //
        btnSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textNewPass.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu mới!", Toast.LENGTH_SHORT).show();
                } else if (textConfirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập lại mật khẩu mới!", Toast.LENGTH_SHORT).show();
                } else {
                    String newPass = textNewPass.getText().toString();
                    String confirm = textConfirm.getText().toString();
                    if(!newPass.equals(confirm)){
                        Toast.makeText(getApplicationContext(), "Mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        User user = SharedPrefManager.getInstance(context).getUser();
                        userid = user.getId();
                        ChangePassword(userid, String.valueOf(newPass));
                    }
                }
            }
        });

    }
    void Mapping(){
        textNewPass = findViewById(R.id.tv_resetpassword_newpassword);
        textConfirm = findViewById(R.id.tv_resetpassword_confirmpassword);
        btnSetPassword = findViewById(R.id.btn_resetpasswprd_reset);
    }
    void ChangePassword(Long userid, String newPassword){
        apiService.changePassword(userid, newPassword).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if(response.isSuccessful()) {
                    object = response.body();
                    Toast.makeText(context, object.getMessage(), Toast.LENGTH_SHORT).show();
                    SharedPrefManager.getInstance(getApplicationContext()).logout();
                    startActivity(new Intent(context, LoginActivity.class));
                }
                else{
                    Toast.makeText(context, "Mật khẩu trùng với mật khẩu cũ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {

            }
        });
    }
}