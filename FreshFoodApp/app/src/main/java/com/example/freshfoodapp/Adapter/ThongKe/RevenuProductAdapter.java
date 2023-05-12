package com.example.freshfoodapp.Adapter.ThongKe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.R;

import java.util.List;

public class RevenuProductAdapter extends RecyclerView.Adapter<RevenuProductAdapter.ViewHolder> {

    Context context;
    List<Product> products;

    public RevenuProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_thongke_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.sold.setText("Đã bán được "+String.valueOf(product.getSold())+" sản phẩm");
        holder.category.setText(product.getCategory().getName());
        holder.name.setText(product.getName());
        Glide.with(context).load(product.getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,category,sold;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_thongke_product_name);
            category = itemView.findViewById(R.id.tv_thongke_product_category);
            sold = itemView.findViewById(R.id.tv_thongke_product_sold);
            img = itemView.findViewById(R.id.iv_thongke_product);
        }
    }


}
