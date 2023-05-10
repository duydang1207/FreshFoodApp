package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.API.ProductAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    ConstraintLayout btnAddToCart;
    TextView proName, proPrice, proDesc, proQuantity;
    ImageView proImg, btnCart, btnBack, btnPlus, btnMinus;
    ProductAPIService productAPIService;

    Product product;
    Context context = this;
    Long productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Mapping();
        productId = getIntent().getLongExtra("id", 0);
        getProductDetail(productId);

        CartEntity cartEntity = new CartEntity();

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct(cartEntity);
                boolean check = false;
                List<CartEntity> carts = AbstractDatabase.getInstance(getApplicationContext()).cartDAO().getAll();
                for (int i = 0; i < carts.size(); i++) {
                    if (carts.get(i).getProductId() == productId) {
                        int quantityNew = cartEntity.getQuantity() + carts.get(i).getQuantity();
                        cartEntity.setQuantity(quantityNew);
                        cartEntity.setId(carts.get(i).getId());
                        check = true;
                        AbstractDatabase.getInstance(getApplicationContext()).cartDAO().update(cartEntity);
                        Toast.makeText(context, "Sản phẩm đã được tăng số lượng!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if (!check) {
                    AbstractDatabase.getInstance(getApplicationContext()).cartDAO().insert(cartEntity);
                    Toast.makeText(getApplicationContext(), "Thêm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, CartActivity.class));
            }
        });
    }

    void getProductDetail(Long id) {
        productAPIService = RetrofitClient.getRetrofit().create(ProductAPIService.class);
        productAPIService.getProduct(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                product = response.body();

                proName.setText(String.valueOf(product.getName()));
                proDesc.setText(String.valueOf(product.getDescription()));
                proPrice.setText(String.valueOf(product.getPrice()));
                Glide.with(context).load(product.getImage()).into(proImg);

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_temp = Integer.parseInt(proQuantity.getText().toString());
                if(quantity_temp<2){
                    Toast.makeText(context, "Đã đạt số lượng giới hạn", Toast.LENGTH_SHORT).show();
                }
                else {
                    quantity_temp--;
                    proQuantity.setText(String.valueOf(quantity_temp));
                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_temp = Integer.parseInt(proQuantity.getText().toString())+1;
                proQuantity.setText(String.valueOf(quantity_temp));
            }
        });
    }

    public void addProduct(CartEntity cartEntity) {
        User user = SharedPrefManager.getInstance(this.getApplicationContext()).getUser();

        cartEntity.setProductId(product.getId());
        cartEntity.setUserId(Long.parseLong(user.getId().toString()));
        cartEntity.setQuantity(Integer.parseInt(proQuantity.getText().toString()));
        cartEntity.setPromotion(product.getPromotion());
        cartEntity.setPrice(product.getPrice().doubleValue());
        cartEntity.setName(product.getName());
        cartEntity.setImage(product.getImage());

    }

    void Mapping() {
        btnAddToCart = (ConstraintLayout) findViewById(R.id.btn_prodt_addcart);
        proName = (TextView) findViewById(R.id.tv_prodt_namepro);
        proPrice = (TextView) findViewById(R.id.tv_prodt_pricepro);
        proImg = (ImageView) findViewById(R.id.ima_prodt_imgpro);
        proDesc = (TextView) findViewById(R.id.tv_prodt_descrpro);
        proQuantity = (TextView) findViewById(R.id.tv_prodt_sl);
        btnCart = (ImageView) findViewById(R.id.iv_productdetail_cart);
        btnBack = (ImageView) findViewById(R.id.iv_productdetail_back);
        btnMinus = (ImageView) findViewById(R.id.btn_productdetail_minus);
        btnPlus = (ImageView) findViewById(R.id.btn_productdetail_plus);

    }
}