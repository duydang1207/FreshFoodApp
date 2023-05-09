package com.example.freshfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import android.widget.Button;
import android.widget.Toast;


import com.example.freshfoodapp.Adapter.CartAdapter;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Orther.SwipeHelper;

import java.math.BigDecimal;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCart;
    public static List<CartEntity> carts;
    public static TextView itemTotalPrice;
    Button btnPlus;
    Button btnMinus;

    private boolean btnDeleteCart;
    private boolean moving = false;
    private int posSwiped = -1;
    private int lastSwiped = -1;


    static TextView totalQuantity;

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

        SwipeHelper swipeHelper = new SwipeHelper(CartActivity.this, rvCart) {
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
//        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                moving = true;
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                moving = false;
//                int position = viewHolder.getAdapterPosition();
//                if (direction == ItemTouchHelper.LEFT) {
//                    //adapter.notifyItemChanged(position);
//                    Toast.makeText(CartActivity.this, "Swipe left", Toast.LENGTH_SHORT).show();
//                }
//                btnDeleteCart = true;
//            }
//
//            @Override
//            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
//                    View itemView = viewHolder.itemView;
//                    float height = itemView.getBottom() - itemView.getTop();
//                    float width = height / 3;
//                    posSwiped = viewHolder.getAdapterPosition();
//
//                    if (dX < 0) {
//                        Paint p = new Paint();
//                        int color = ContextCompat.getColor(getApplicationContext(), R.color.colordelete);
//                        float deleteBtnLeft = itemView.getRight() + dX;
//                        p.setColor(color);
//                        RectF buttonDelete = new RectF(itemView.getRight() + dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
//                        c.drawRect(buttonDelete, p);
//                        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_delete);
//                        float margin = (dX / 3 - width) / 2;
//                        RectF iconDest =new RectF(itemView.getRight() + margin, itemView.getTop() + width, itemView.getRight() + (margin + width), itemView.getBottom() - width);
//                        c.drawBitmap(icon, null, iconDest, p);
//
//                        if (dX <= - deleteBtnLeft){
//                            btnDeleteCart = true;
//                            moving = false;
//                        }else {
//                            btnDeleteCart = false;
//                            moving = true;
//                        }
//                        if (dX == 0.0f){
//                            moving = false;
//                        }
//
//                        if (btnDeleteCart){
//                            clickButtonDeleteProCartListener(recyclerView, viewHolder, posSwiped);
//                        }
//                    }
//                }
//                super.onChildDraw(c, recyclerView, viewHolder, dX/3, dY, actionState, isCurrentlyActive);
//            }
//
//            private void clickButtonDeleteProCartListener(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int posSwiped) {
//                viewHolder = recyclerView.findViewHolderForAdapterPosition(posSwiped);
//                View itemView = viewHolder.itemView;
//
//
//                itemView.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent e) {
//                        if (posSwiped < 0)
//                        {
//                            return false;
//                        }
//                        Point point = new Point((int) e.getRawX(), (int) e.getRawY());
//
//                        Rect rect = new Rect();
//                        itemView.getGlobalVisibleRect(rect);
//                        if (e.getAction() == MotionEvent.ACTION_DOWN || e.getAction() == MotionEvent.ACTION_UP ||e.getAction() == MotionEvent.ACTION_MOVE) {
//                            if (rect.top < point.y && rect.bottom > point.y)
////                                gestureDetector.onTouchEvent(e);
//                                Toast.makeText(getApplicationContext(), "vl", Toast.LENGTH_SHORT).show();
//                            else {
////                                recoverQueue.add(swipedPos);
////                                posSwiped = -1;
////                                recoverSwipedItem();
//                            Toast.makeText(getApplicationContext(), "đã xóa", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                        return false;
//                    }
//                });
//            }
//
//        };



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

}