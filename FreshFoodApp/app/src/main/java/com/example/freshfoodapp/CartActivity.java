package com.example.freshfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfoodapp.Adapter.CartAdapter;
import com.example.freshfoodapp.Models.Cart;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.adapter.ListViewAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Cart> carts;
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


        //Mapping();
        //Tạo Adapter
        carts = cartList();
        listView = (ListView) findViewById(R.id.lv_cart_products);
        cartAdapter = new CartAdapter(CartActivity.this, carts);
        //truyền dữ liệu từ adapter ra listview
        listView.setAdapter(cartAdapter);

        final SwipeToDismissTouchListener<ListViewAdapter> touchListener =
                new SwipeToDismissTouchListener<>(
                        new ListViewAdapter(listView),
                        new SwipeToDismissTouchListener.DismissCallbacks<ListViewAdapter>() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListViewAdapter view, int position) {
                                cartAdapter.remove(position);
                                Toast.makeText(CartActivity.this, "Position " + position, Toast.LENGTH_SHORT).show();
                            }
                        });

        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener((AbsListView.OnScrollListener) touchListener.makeScrollListener());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (touchListener.existPendingDismisses()) {
                    touchListener.undoPendingDismiss();
                    Toast.makeText(CartActivity.this, "Đã hủy xóa", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CartActivity.this, "Position " + position, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void Mapping() {
        listView = (ListView) findViewById(R.id.lv_cart_products);

        //Thêm dữ liệu vào List
        carts = new ArrayList<>();
        carts.add(new Cart(1,"Cá basa tươi sống dfak fdsfsad  fsdfdsa ffdsafsda",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(2,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),7,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(3,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(4,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),10,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(5,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
        carts.add(new Cart(5,"Cá basa tươi sống",2, BigDecimal.valueOf(15000),0,"http://file.freshfoods.vn/global/ba-chi-bo-my-cat-lat-ff-01-01.jpg"));
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