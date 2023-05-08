package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.freshfoodapp.API.APIService;
import com.example.freshfoodapp.API.CartAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Models.Account;
import com.example.freshfoodapp.Models.Category;
import com.example.freshfoodapp.Models.Inventory;
import com.example.freshfoodapp.Models.OrderItem;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.ResponseObject;
import com.example.freshfoodapp.Models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    ConstraintLayout btn_loginGoogle, btnConfirm, layoutLogin;
    EditText username, password;

    APIService apiService = RetrofitClient.getRetrofit().create(APIService.class);

    CartAPIService cartApi = RetrofitClient.getRetrofit().create(CartAPIService.class);

    ResponseObject<Account> object;

    ResponseObject<User> objectUser;

    Account account;
    User user;

    TextView signUpBtn;

    String googleEmail, googleName;
    boolean signupGG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mapping();
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            Log.d("message", "Success");
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        btn_loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInWithGoogle();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNoneEditText(view);
                if (username.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập username!", Toast.LENGTH_SHORT).show();
                } else if (password.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                } else {
                    Login(String.valueOf(username.getText()), String.valueOf(password.getText()));
                }
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNoneEditText(view);
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        layoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNoneEditText(view);
            }
        });
    }

    //khi an vao khong phải edittext
    void clickNoneEditText(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginActivity.this);
                    return false;
                }
            });
        }
    }

    //an ban phim
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    void Mapping() {
        btnConfirm = findViewById(R.id.btn_login_signin);
        signUpBtn = findViewById(R.id.btn_login_signup);
        //Login with google
        btn_loginGoogle = findViewById(R.id.btn_login_google);
        username = findViewById(R.id.et_login_username);
        password = findViewById(R.id.et_login_password);

        layoutLogin = findViewById(R.id.layout_login);
    }

    void SignInWithGoogle() {
        Intent signinInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signinInIntent, 1000);
    }

    void getCartByAccount(Long id){
        cartApi.getCart(id).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                ResponseObject<OrderItem> objectCart = new ResponseObject<>();
                if(response.isSuccessful()){
                    objectCart = response.body();
                    Gson gson = new Gson();
                    Type typeListOrderItem = new TypeToken<ArrayList<OrderItem>>(){}.getType();
                    if(objectCart.getStatus().compareTo("success")==0) {
                        List<OrderItem> orderItems = gson.fromJson(objectCart.getData(), typeListOrderItem);
                        for (int i = 0; i < orderItems.size(); i++) {
                            OrderItem orderItem = orderItems.get(i);

                            Product product = orderItem.getInventory().getProduct();

                            CartEntity cart = new CartEntity();
                            cart.setProductCart(product);
                            cart.setQuantity(orderItem.getQuantity());
                            cart.setUserId(id);
                            AbstractDatabase.getInstance(getApplicationContext()).cartDAO().insert(cart);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),objectCart.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    void Login(String username, String password) {
        apiService.login(username, password).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.isSuccessful()) {
                    //luu login
                    objectUser = response.body();
                    Gson gson = new Gson();
                    user = gson.fromJson(objectUser.getData(), User.class);
                    if (user == null) {
                        signupGG = true;
                    } else {
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        getCartByAccount(user.getId());
                        Intent intent = new Intent(LoginActivity.this, UploadAvatarActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    signupGG = true;
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                getAccountGoogle();
                Login(googleEmail, "");
                if (signupGG) {
                    Signup(googleEmail, googleName);
                }
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    void getAccountGoogle() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            googleName = account.getDisplayName();
            googleEmail = account.getEmail();
        }
    }

    void Signup(String email, String name) {
        apiService.signup(email, "", name, email).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                if (response.isSuccessful()) {
                    Login(googleEmail, "");
                    Toast.makeText(getApplicationContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}