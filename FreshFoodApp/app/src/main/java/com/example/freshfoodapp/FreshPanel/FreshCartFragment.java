package com.example.freshfoodapp.FreshPanel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfoodapp.API.CartAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Adapter.CartAdapter;
import com.example.freshfoodapp.CartActivity;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Models.Inventory;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.Models.ProductQuantity;
import com.example.freshfoodapp.Models.ResponseObject;
import com.example.freshfoodapp.OrderActivity;
import com.example.freshfoodapp.Orther.SwipeHelper;
import com.example.freshfoodapp.R;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreshCartFragment extends Fragment {
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

    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.activity_cart, container, false);
        Mapping();
        carts = AbstractDatabase.getInstance(getActivity().getApplicationContext()).cartDAO().getAll();
        CartAdapter adapter = new CartAdapter(getActivity().getApplicationContext(), carts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        TotalPrice();
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkProduct(carts);
            }
        });

        SwipeHelper swipeHelper = new SwipeHelper(getActivity().getApplicationContext(), rvCart) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Xóa",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                // TODO: onDelete
                                Log.d("Alert","Deleted");
                            }
                        }
                ));
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeHelper);
        itemTouchHelper.attachToRecyclerView(rvCart);

        return v;
    }
    void deleteProductFailed(List<Long> id){
        AbstractDatabase database = AbstractDatabase.getInstance(getActivity().getApplicationContext());
        for(int i =0; i<id.size();i++){
            database.cartDAO().deleteProduct(id.get(i));
        }
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

                    Intent intent= new Intent(getActivity().getApplicationContext(), OrderActivity.class);
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
        rvCart = (RecyclerView) v.findViewById(R.id.lv_cart_products);
        itemTotalPrice = (TextView) v.findViewById(R.id.tv_cart_totalPrice);
        btnMinus = v.findViewById(R.id.btn_cartItem_minus);
        btnPlus = v.findViewById(R.id.btn_cartItem_plus);
        btnPayment = v.findViewById(R.id.btn_cart_payment);
        totalQuantity = v.findViewById(R.id.tv_cart_quantity_total);
    }
}
