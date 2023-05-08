package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.Button;
import android.widget.ListAdapter;


import com.example.freshfoodapp.Adapter.CartAdapter;
import com.example.freshfoodapp.Adapter.CartAdapterEntity;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Models.Cart;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCart;
    public static List<CartEntity> carts;
    public static TextView itemTotalPrice;
    Button btnPlus;
    Button btnMinus;

    static TextView totalQuantity;
    CartAdapter cartAdapter;

    private int[] myImageList = new int[]{R.drawable.fish_ngu,R.drawable.meat_beef,
            R.drawable.meat_pig, R.drawable.tom, R.drawable.fish_ngu};
    private String[] titlePro = new String[]{"Cá basa tươi sống","Cá basa tươi sống",
    "Cá basa tươi sống","Cá basa tươi sống","Cá basa tươi sống"};
    private int[] proFee = new int[]{15000,14000,25000,30000,10000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


//        //Mapping();
//        //Tạo Adapter
//        carts = cartList();
//        listView = (ListView) findViewById(R.id.lv_cart_products);
//        cartAdapter = new CartAdapter(CartActivity.this, carts);
//        //truyền dữ liệu từ adapter ra listview
//        listView.setAdapter(cartAdapter);
        Mapping();

        carts = AbstractDatabase.getInstance(getApplicationContext()).cartDAO().getAll();
        CartAdapterEntity adapter = new CartAdapterEntity(getApplicationContext(), carts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        TotalPrice();
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);

//        final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
//                new SwipeToDismissTouchListener<>(
//                        new ListViewAdapter(listView),
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
        totalQuantity = findViewById(R.id.tv_cart_quantity_total);
    }
    private ArrayList<Cart> cartList(){
        ArrayList<Cart> cartlst = new ArrayList<>();

        for (int i=0; i < 5; i++){
            Cart cart = new Cart();
            cart.setName(titlePro[i]);
            cart.setImage(String.valueOf(myImageList[i]));
            cart.setPromotion(0);
            cart.setPrice(BigDecimal.valueOf(proFee[i]));
            cartlst.add(cart);
        }

        return cartlst;
    }
}