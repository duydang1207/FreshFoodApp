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

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Models.OrderList;
import com.example.freshfoodapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrderListViewAdapter extends BaseAdapter {
    private final String btnstt;
    private Context context;
    private int layout;
    private List<OrderList> orders;



    public OrderListViewAdapter(Context context, int layout , ArrayList<OrderList> orders, String btnstt) {
        this.context = context;
        this.orders = orders;
        this.layout = layout;
        this.btnstt = btnstt;
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
        return orders.get(i).getOrder_id();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //lấy context
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới


//        View viewOrder;
//        if (view == null) {
//            viewOrder = View.inflate(viewGroup.getContext(), R.layout.order_view, null);
//        } else viewOrder = view;
        view = inflater.inflate(layout,null);

        OrderList order = (OrderList) getItem(i);
        TextView textName = (TextView) view.findViewById(R.id.name_Product);

        TextView textPrice = (TextView) view.findViewById(R.id.price_Product);
        TextView textQuantity = (TextView) view.findViewById(R.id.quantity);
        TextView textTotal =  view.findViewById(R.id.total_Price);
        ImageView image = view.findViewById(R.id.image_product);
        Button btn = view.findViewById(R.id.btn_order_list);



        textName.setText(order.getName_product());
        textPrice.setText(String.valueOf("Giá: " +order.getPrice_product()));
        textQuantity.setText(String.valueOf("Số lượng: "+ order.getQuantity()));
        textTotal.setText(String.valueOf("Tổng: "+ order.getTotal_price()));
        btn.setText(btnstt);
        Glide.with(context).load(order.getImage_product()).into(image);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Huy", Toast.LENGTH_SHORT).show();
            }
        });
        //trả về view
        return view;
    }
}