package com.example.freshfoodapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshfoodapp.Models.OrderStatus;
import com.example.freshfoodapp.R;

import java.util.List;

public class OrderListViewAdapter extends BaseAdapter {
    private final String mfragment;
    private final List<OrderStatus> orders;
    private Context context;
    private int layout;



    public OrderListViewAdapter(Context context, int layout , List<OrderStatus> orders, String mfragment) {
        this.context = context;
        this.orders = orders;
        this.layout = layout;
        this.mfragment = mfragment;
    }


    @Override
    public int getCount() {
        return orders==null?0:orders.size();
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
        view = inflater.inflate(layout,null);

        OrderStatus order = (OrderStatus) getItem(i);
        TextView textName = (TextView) view.findViewById(R.id.name_Product);
        TextView textPrice = (TextView) view.findViewById(R.id.price_Product);
        TextView textQuantity = (TextView) view.findViewById(R.id.quantity);
        TextView textTotal =  view.findViewById(R.id.total_Price);
        ImageView image = view.findViewById(R.id.image_product);
        Button btn = view.findViewById(R.id.btn_order_list);

        textName.setText(order.getAddress());
        textPrice.setText(String.valueOf("Giá: " +order.getId()));
        textQuantity.setText(String.valueOf("Số lượng: "+ order.getPaymentComplete()));
        textTotal.setText(String.valueOf("Tổng: "+ order.getPaymentComplete()));
        switch (mfragment) {
            case "confirm":
                btn.setText("Xem sản phẩm");
                break;
            case "delivering":
                btn.setText("Xác nhận đã nhận");
                break;
            case "received":
                btn.setText("Mua lại");
                break;
            case "cancelled":
                btn.setText("Chi tiết hủy");
                break;
        }
//        Glide.with(context).load(order.getImage_product()).into(image);
//        image.setScaleType(ImageView.ScaleType.FIT_XY);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mfragment) {
                    case "confirm":
                        btnconfirm();
                        break;
                    case "delivering":
                        btndelivering();
                        break;
                    case "received":
                        btnreceived();
                        break;
                    case "cancelled":
                        btncancelled();
                        break;
                }

            }
        });
        //trả về view
        return view;
    }
    public  void btnconfirm()
    {
        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
    }
    public  void btndelivering()
    {
        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
    }
    public  void btnreceived()
    {
//        Intent intent = new Intent(context, ProductDetailActivity.class);
//        intent.putExtra("id", 0);
//        startActivity();
        Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
    }



    public  void btncancelled()
    {
        Toast.makeText(context, "4", Toast.LENGTH_SHORT).show();
    }
}