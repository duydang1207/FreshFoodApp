package com.example.freshfoodapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import android.widget.Button;
import android.widget.Toast;


import com.example.freshfoodapp.API.CartAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Adapter.CartAdapter;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Models.Inventory;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.ProductQuantity;
import com.example.freshfoodapp.Models.ResponseObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCart;
    public static List<CartEntity> carts;
    public static TextView itemTotalPrice;
    Button btnPlus;
    Button btnMinus;
    List<Product> productsFaile;
    List<Inventory> inventories;
    Button btnPayment;

    Boolean isTrue = true;
    ResponseObject<ProductQuantity> responseObject;
    ProductQuantity sendData;

    static TextView totalQuantity;
    CartAPIService apiService = RetrofitClient.getRetrofit().create(CartAPIService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Mapping();

        carts = AbstractDatabase.getInstance(getApplicationContext()).cartDAO().getAll();
        CartAdapter adapter = new CartAdapter(getApplicationContext(), carts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        TotalPrice();
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkProduct(carts);
            }
        });
//        final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
//                new SwipeToDismissTouchListener<>(
//                        new ListViewAdapter(rvCart),
//                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
//                            @Override
//                            public boolean canDismiss(int position) {
//                                return true;
//                            }
//
//                            @Override
//                            public void onDismiss(ListViewAdapter view, int position) {
//                                cartAdapter.remove(position);
//                                Toast.makeText(CartActivity.this, "Position " + position, Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//        listView.setOnTouchListener(touchListener);
//        listView.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (touchListener.existPendingDismisses()) {
//                    touchListener.undoPendingDismiss();
//                    Toast.makeText(CartActivity.this, "Đã hủy xóa", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(CartActivity.this, "Position " + position, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

    void deleteProductFailed(List<Long> id){
        AbstractDatabase database = AbstractDatabase.getInstance(getApplicationContext());
        for(int i =0; i<id.size();i++){
            database.cartDAO().deleteProduct(id.get(i));
        }
    }

    public void showAlertDialog(Context context)  {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set Title and Message:
        builder.setTitle("Thông báo").setMessage("Một số sản phẩm bạn chọn đã hết! " +
                "/nXin phép xóa các sản phẩm đó ra khỏi giỏ hàng");

        //
        builder.setCancelable(true);

        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context,"Xin lỗi vì sự bất tiện này!Sản phẩm sẽ sớm được cửa hàng bổ sung thêm",
                        Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });


        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }
    void checkProduct(List<CartEntity> carts){
        List<Long> productId = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        for(int i =0 ;i <carts.size();i++){
            CartEntity cart = carts.get(i);
            productId.add(cart.getProductId());
            quantity.add(cart.getQuantity());
        }
        ProductQuantity productQuantity = new ProductQuantity(productId,quantity);

        apiService.checkProduct(productQuantity).enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                responseObject = response.body();
                Gson gson = new Gson();
                if(responseObject.getStatus().compareTo("success")==0){

                    sendData = gson.fromJson(responseObject.getData(),ProductQuantity.class);

                    Intent intent= new Intent(getApplicationContext(),OrderActivity.class);
                    intent.putExtra("list", sendData);
                    startActivity(intent);
                }
                else{
                    sendData = gson.fromJson(responseObject.getData(),ProductQuantity.class);
                    deleteProductFailed(sendData.getId());
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.e("CartActivity CheckProduct",t.getMessage());
                isTrue=false;
            }
        });

    }
    public static void TotalPrice(){
        BigDecimal total = BigDecimal.valueOf(0);
        int quantity = 0;
        for(int i =0;i<carts.size();i++)
        {
            quantity += carts.get(i).getQuantity();
            total = total.add(BigDecimal.valueOf(carts.get(i).getPrice()).multiply(BigDecimal.valueOf(quantity)));
        }
        totalQuantity.setText(String.valueOf(quantity));
        itemTotalPrice.setText(String.valueOf("$ " + total));
    }


    private void Mapping() {
        rvCart = (RecyclerView) findViewById(R.id.lv_cart_products);
        itemTotalPrice = (TextView) findViewById(R.id.tv_cart_totalPrice);
        btnMinus = findViewById(R.id.btn_cartItem_minus);
        btnPlus = findViewById(R.id.btn_cartItem_plus);
        btnPayment = findViewById(R.id.btn_cart_payment);
        totalQuantity = findViewById(R.id.tv_cart_quantity_total);
    }

}