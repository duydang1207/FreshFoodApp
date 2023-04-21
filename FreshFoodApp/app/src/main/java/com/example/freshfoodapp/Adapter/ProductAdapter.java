package com.example.freshfoodapp.Adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Domain.ProductDomain;
import com.example.freshfoodapp.R;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<ProductDomain> productDomains;

    public ProductAdapter(ArrayList<ProductDomain> productDomains) {
        this.productDomains = productDomains;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_product, parent, false);
        return new ProductAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.proName.setText(productDomains.get(position).getTitle());
        holder.proPrice.setText(String.valueOf(productDomains.get(position).getFee()));
        String url = "";
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(productDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imgPro);

    }

    @Override
    public int getItemCount() {
        return productDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView proName, proPrice;
        ImageView imgPro;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proName = itemView.findViewById(R.id.tv_proname);
            proPrice = itemView.findViewById(R.id.tv_proprice);
            imgPro = itemView.findViewById(R.id.img_product);
        }
    }
}
