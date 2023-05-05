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
import com.example.freshfoodapp.Domain.CategoryDomain;
import com.example.freshfoodapp.ProductDetailActivity;
import com.example.freshfoodapp.ProductOfCategoryActivity;
import com.example.freshfoodapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryDomain> categoryDomains;
    Context context;

    public CategoryAdapter(Context context, ArrayList<CategoryDomain> categoryDomains) {
        this.context = context;
        this.categoryDomains = categoryDomains;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.tv_cateName.setText(categoryDomains.get(position).getTitle());
        String url = "";
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(categoryDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imgCate);
        holder.category = categoryDomains.get(position).getTitle();
        holder.itemCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductOfCategoryActivity.class);
                intent.putExtra("catename", holder.category);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cateName;
        ImageView imgCate;
        ConstraintLayout itemCate;

        String category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cateName = itemView.findViewById(R.id.tv_namecate);
            imgCate = itemView.findViewById(R.id.img_homepage_cate);
            itemCate = itemView.findViewById(R.id.item_categoryitem_item);

        }
    }
}
