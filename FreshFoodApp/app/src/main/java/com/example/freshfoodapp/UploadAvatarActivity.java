package com.example.freshfoodapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.API.APIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.FreshPanel.File.RealPathUtil;
import com.example.freshfoodapp.Models.ResponseObject;
import com.example.freshfoodapp.Models.User;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadAvatarActivity extends AppCompatActivity {

    ImageView btn_back;
    Button btn_chooseFile, btn_upload;
    ImageView avatar;

    Long id =null;

    APIService apiService;

    Uri uri;

    ResponseObject<User> data;
    private static final int MY_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_avatar);
        Mapping();
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        id = user.getId();
        if(user.getAvatar()!=null)
            Glide.with(getApplicationContext()).load(user.getAvatar()).into(avatar);
        //click back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadAvatarActivity.this, ProfileActivity.class));
            }
        });

        btn_chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImageToProfile();
            }
        });

    }
    private void uploadImageToProfile() {
        String pathFileImg = RealPathUtil.getRealPath(this, uri);
        File file = new File(pathFileImg);
        RequestBody requestBodyId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(id));
        RequestBody requestBodyImg = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestBodyImg);

        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.uploadImage(requestBodyId,multipartBody).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error UploadImage",t.getMessage());
            }
        });

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }
    public void onClickRequestPermission() {
//        > android 6 ms check
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }
    private ActivityResultLauncher<Intent> mActivityResultLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e("Tag", "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null)
                            return;
                        uri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            avatar.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

    );
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLaucher.launch(Intent.createChooser(intent, "Select Picture"));
    }
    void Mapping(){
        avatar = findViewById(R.id.iv_updateavatar_avatar);
        btn_upload = findViewById(R.id.btn_updateavatar_uploadavatar);
        btn_chooseFile = findViewById(R.id.btn_updateavatar_choosefile);
        btn_back = findViewById(R.id.btn_uploadavatar_back);
    }
}