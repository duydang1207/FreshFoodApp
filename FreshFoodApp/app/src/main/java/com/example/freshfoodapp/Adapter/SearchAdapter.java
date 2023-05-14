package com.example.freshfoodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Models.Product;
import com.example.freshfoodapp.ProductDetailActivity;
import com.example.freshfoodapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {
    List<Product> products;
    List<Product> productsold;
    Context context;

    public SearchAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        this.productsold = products;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pro_order, parent, false);
        return new SearchViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.proName.setText(products.get(position).getName());
        holder.proPrice.setText(String.valueOf(products.get(position).getPrice()));
        Glide.with(context).load(products.get(position).getImage()).into(holder.imgPro);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent().setClass(context, ProductDetailActivity.class);
                i.putExtra("id", products.get(position).getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                // Launch the new activity and add the additional flags to the intent
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return products==null?0:products.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String keySearch = charSequence.toString();
                if(keySearch.isEmpty()){
                    products = productsold;
                }
                else {
                    List<Product> list = new ArrayList<>();
                    for(Product product : productsold){
                        if(product.getName().toLowerCase().contains(keySearch.toLowerCase())){
                            list.add(product);
                        }
                    }
                    products = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = products;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                products = (List<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView proName, proPrice;
        ImageView imgPro;

        LinearLayout item;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            proName = itemView.findViewById(R.id.tv_orderItem_name);
            proPrice = itemView.findViewById(R.id.tv_orderItem_price);
            imgPro = itemView.findViewById(R.id.iv_orderItem_product);
            item = itemView.findViewById(R.id.item_pro_productitem);
        }
    }
}
