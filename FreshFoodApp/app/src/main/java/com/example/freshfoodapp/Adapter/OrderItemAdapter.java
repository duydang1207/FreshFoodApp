package com.example.freshfoodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.CartActivity;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.Models.OrderItem;
import com.example.freshfoodapp.ProductDetailActivity;
import com.example.freshfoodapp.R;

import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder>{

    private final Context context;
    List<OrderItem> orderItemList;
    
    public OrderItemAdapter(Context context, List<OrderItem> orderItemList)
    {
        this.orderItemList=orderItemList;
        this.context= context;
    }
    public OrderItemAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);

        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemAdapter.OrderItemViewHolder holder, int position) {
        OrderItem orderItem = orderItemList.get(position);
        holder.quantity.setText(String.valueOf("x"+ orderItem.getQuantity()));
        holder.name_Product.setText(orderItem.getInventory().getProduct().getName());
        holder.price_Product.setText(String.valueOf(orderItem.getInventory().getProduct().getPrice()+" vnđ"));
        Glide.with(context).load(orderItem.getInventory().getProduct().getImage()).into(holder.image_product);
        holder.image_product.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.product_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("id", orderItem.getInventory().getProduct().getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderItemList==null?0:orderItemList.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder {
        TextView name_Product, price_Product, quantity;
        ImageView image_product;

        LinearLayout product_id;

        Long id;
        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name_Product = itemView.findViewById(R.id.name_Product);
            price_Product = itemView.findViewById(R.id.price_Product);
            quantity = itemView.findViewById((R.id.quantity));
            image_product = itemView.findViewById(R.id.image_product);
            product_id = itemView.findViewById(R.id.product_id);

        }
    }
}
