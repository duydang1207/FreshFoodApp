package com.example.freshfoodapp.Adapter;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfoodapp.API.OrderAPIService;
import com.example.freshfoodapp.API.RetrofitClient;
import com.example.freshfoodapp.Models.OrderItem;
import com.example.freshfoodapp.Models.Orders;
import com.example.freshfoodapp.OrderListActivity;
import com.example.freshfoodapp.OrderListAdminActivity;
import com.example.freshfoodapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

;

public class OrderStatusAdminAdapter extends BaseAdapter {
    private final List<Orders> orders;
    private Context context;
    private int layout;

    Orders responseObject;
    List<OrderItem> orderItemList = new ArrayList<>();
    OrderAPIService orderAPIService = RetrofitClient.getRetrofit().create(OrderAPIService.class);

    OrderItemAdapter orderItemAdapter;

    View finalView, view;
    static RecyclerView recyclerView;
    TextView time, total_price, status, address, phone;
    Button btn_update_status, btn_update_status2;
    String confirmation_question = "", confirmation_question2 = "";
    int status_change, status_change2;

    public OrderStatusAdminAdapter(Context context, int layout, List<Orders> orders) {
        this.context = context;
        this.orders = orders;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return orders == null ? 0 : orders.size();
    }

    @Override
    public Object getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //lấy context
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        Orders order = (Orders) getItem(i);

        View finalView = view;
        orderAPIService.getOrderItem(order.getId()).enqueue(new Callback<List<OrderItem>>() {
            @Override
            public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                time = finalView.findViewById(R.id.time_buy);
                address = finalView.findViewById(R.id.address);
                phone = finalView.findViewById(R.id.phone);
                status = finalView.findViewById(R.id.status);
                total_price = finalView.findViewById(R.id.total_price);
                btn_update_status = finalView.findViewById(R.id.btn_update_stt);
                btn_update_status2 = finalView.findViewById(R.id.btn_update_stt2);
                recyclerView = finalView.findViewById(R.id.listOrderItem);
                orderItemList = response.body();

                orderItemAdapter = new OrderItemAdapter(context, orderItemList);
                GridLayoutManager gridLayoutManager =
                        new GridLayoutManager(context, 1);

                recyclerView.setLayoutManager(gridLayoutManager);
                time.setText(String.valueOf(order.getPaymentComplete()));
                total_price.setText(String.valueOf(order.getTotal_price()));

                switch (order.getStatus()) {
                    case 0:
                        status.setText("Chờ xác nhận");
                        btn_update_status.setText("Xác nhận");
                        btn_update_status2.setText("Hủy đơn hàng");
                        confirmation_question = "Xác nhận giao đơn hàng";
                        confirmation_question2 = "Bạn muốn hủy đơn hàng này không?";
                        status_change = 1;
                        status_change2 = 3;
                        break;
                    case 1:
                        status.setText("Đang giao");
                        btn_update_status.setText("Đã giao");
                        btn_update_status2.setText("Hủy đơn hàng");
                        confirmation_question = "Xác nhận đã giao";
                        confirmation_question2 = "Bạn muốn hủy đơn hàng này không?";
                        status_change = 2;
                        break;
                    case 2:
                        status.setText("Đã nhận");
                        btn_update_status.setVisibility(finalView.INVISIBLE);
                        btn_update_status2.setVisibility(finalView.INVISIBLE);
                        break;
                    case 3:
                        status.setText("Đã hủy");
                        btn_update_status.setVisibility(finalView.INVISIBLE);
                        btn_update_status2.setVisibility(finalView.INVISIBLE);
                        break;
                }
                recyclerView.setAdapter(orderItemAdapter);
                btn_update_status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickBtn(status_change, order, confirmation_question);
                    }

                });
                btn_update_status2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickBtn(status_change2, order, confirmation_question2);

                    }

                });

            }

            @Override
            public void onFailure(Call<List<OrderItem>> call, Throwable t) {
            }
        });
        return view;
    }

    private void onClickBtn(int stt, Orders orders, String question) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(question)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Xóa dữ liệu
                        orderAPIService.updateStatus(stt, orders.getId()).enqueue(new Callback<Orders>() {
                            @Override
                            public void onResponse(Call<Orders> call, Response<Orders> response) {
                                responseObject = response.body();
                            }

                            @Override
                            public void onFailure(Call<Orders> call, Throwable t) {


                            }
                        });
                        Intent intent = new Intent(context, OrderListAdminActivity.class);
                        intent.putExtra("fragment", orders.getStatus());
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Hủy bỏ xóa dữ liệu
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}

