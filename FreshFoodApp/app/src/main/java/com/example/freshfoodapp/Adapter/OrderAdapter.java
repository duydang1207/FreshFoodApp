package com.example.freshfoodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    List<CartEntity> list;

    public OrderAdapter(Context context, List<CartEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pro_order,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        CartEntity item = list.get(position);
        holder.name.setText(item.getName());
        holder.quantity.setText("x"+String.valueOf(item.getQuantity()));
        holder.price.setText(String.valueOf(item.getPrice()));
        Glide.with(context).load(item.getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,price,quantity;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_orderItem_product);
            name = itemView.findViewById(R.id.tv_orderItem_name);
            price = itemView.findViewById(R.id.tv_orderItem_price);
            quantity = itemView.findViewById(R.id.tv_orderItem_quantity);
        }
    }

}
