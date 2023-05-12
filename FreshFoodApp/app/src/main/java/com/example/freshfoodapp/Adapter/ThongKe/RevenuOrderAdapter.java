package com.example.freshfoodapp.Adapter.ThongKe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfoodapp.Models.Orders;
import com.example.freshfoodapp.R;

import java.util.List;

public class RevenuOrderAdapter extends RecyclerView.Adapter<RevenuOrderAdapter.ViewHolder>{
    Context context;
    List<Orders> orderList;

    public RevenuOrderAdapter(Context context, List<Orders> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id,name,create,price,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.tv_thongke_order_id);
            name= itemView.findViewById(R.id.tv_thongke_order_name);
            create= itemView.findViewById(R.id.tv_thongke_order_create);
            price= itemView.findViewById(R.id.tv_thongke_order_price);
            status= itemView.findViewById(R.id.tv_thongke_order_status);



        }
    }
    @NonNull
    @Override
    public RevenuOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_thongke_order,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RevenuOrderAdapter.ViewHolder holder, int position) {
        if(position==0) {
            holder.id.setText("Mã đơn");
            holder.id.setTypeface(null, Typeface.BOLD);

            holder.name.setText("Tên khách hàng");
            holder.name.setTypeface(null, Typeface.BOLD);

            holder.create.setText("Ngày đặt");
            holder.create.setTypeface(null, Typeface.BOLD);

            holder.status.setText("Trạng thái");
            holder.status.setTypeface(null, Typeface.BOLD);

            holder.price.setText("Tổng tiền");
            holder.price.setTypeface(null, Typeface.BOLD);

        }
        else {
            Orders order = orderList.get(position - 1);
            holder.id.setText(String.valueOf(order.getId()));
            holder.price.setText(String.valueOf(order.getTotal_price()));
            holder.name.setText(String.valueOf(order.getUser().getName()));
            holder.create.setText(String.valueOf(order.getPaymentComplete()));
            switch (order.getStatus()) {
                case 0:
                    holder.status.setTextColor(Color.BLUE);
                    holder.status.setText("Chờ xác nhận");
                    break;
                case 1:
                    holder.status.setTextColor(Color.YELLOW);
                    holder.status.setText("Đang giao");
                    break;
                case 2:
                    holder.status.setTextColor(Color.GREEN);
                    holder.status.setText("Đã nhận");
                    break;
                case 3:
                    holder.status.setTextColor(Color.RED);
                    holder.status.setText("Đã hủy");
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
