package com.example.freshfoodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.ProductDetailActivity;
import com.example.freshfoodapp.R;

import java.util.List;


public class ProductSoldCategoryAdapter extends RecyclerView.Adapter<ProductSoldCategoryAdapter.ViewHolder> {
    List<Product> productDomains;
    Context context;

    public ProductSoldCategoryAdapter(Context context, List<Product> productDomains) {
        this.context = context;
        this.productDomains = productDomains;
    }

    @NonNull
    @Override
    public ProductSoldCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_product, parent, false);
        return new ProductSoldCategoryAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSoldCategoryAdapter.ViewHolder holder, int position) {
        if(productDomains.get(position).getName().length()>35){
            productDomains.get(position).setName(productDomains.get(position).getName().substring(0,30).concat("..."));
        }
        holder.proName.setText(productDomains.get(position).getName());
        holder.proPrice.setText(String.valueOf(productDomains.get(position).getPrice()));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(productDomains.get(position).getImage(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(productDomains.get(position).getImage())
                .into(holder.imgPro);
        holder.id = productDomains.get(position).getId();


        holder.itemPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ProductDetailActivity.class);
//                intent.putExtra("id", holder.id);
//                context.startActivity(intent);

                Intent i = new Intent().setClass(context, ProductDetailActivity.class);
                i.putExtra("id", holder.id);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                // Launch the new activity and add the additional flags to the intent
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView proName, proPrice;
        ImageView imgPro;
        ConstraintLayout itemPro;
        Long id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            proName = itemView.findViewById(R.id.tv_proname);
            proPrice = itemView.findViewById(R.id.tv_proprice);
            imgPro = itemView.findViewById(R.id.img_product);
            itemPro = itemView.findViewById(R.id.item_itemproduct_itemproduct);
        }
    }
}
