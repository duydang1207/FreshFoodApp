package com.example.freshfoodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.CartActivity;
import com.example.freshfoodapp.Database.AbstractDatabase;
import com.example.freshfoodapp.Entity.CartEntity;
import com.example.freshfoodapp.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    List<CartEntity> cartList;

    public CartAdapter(Context context, List<CartEntity> cartList) {
        this.context = context;
        this.cartList = cartList;
    }
    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        CartEntity cart = cartList.get(position);
        holder.tvName.setText(cart.getName());
        holder.tvPrice.setText(String.valueOf("$ "+cart.getPrice()));
        holder.tvPromotion.setText(String.valueOf(cart.getPrice()*(1+(double)cart.getPromotion()/100)));
        holder.tvQuantity.setText(String.valueOf(cart.getQuantity()));
//        BigDecimal total = cart.getQuantity() * cart.getPrice();
//        holder.tvToTalPrice.setText(String.valueOf("$ "+total));
        Glide.with(context).load(cart.getImage()).into(holder.imgProduct);

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = cart.getQuantity()+1;
                cart.setQuantity(quantity);
                resetCart(holder,cart);
                CartActivity.TotalPrice();
            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = cart.getQuantity();
                if(quantity>0) {
                    quantity--;
                    cart.setQuantity(quantity);
                    resetCart(holder,cart);
                    CartActivity.TotalPrice();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void resetCart(@NonNull CartAdapter.CartViewHolder holder, CartEntity cart){
        AbstractDatabase.getInstance(context).cartDAO().update(cart);
        holder.tvQuantity.setText(String.valueOf(cart.getQuantity()));
        holder.tvQuantity.setText(String.valueOf(cart.getQuantity()));
//        double total = cart.getQuantity() * cart.getPrice();
//        holder.tvToTalPrice.setText(String.valueOf("$ "+total));
    }
    public void deleteProductCart(@NonNull CartAdapter.CartViewHolder holder, CartEntity cart){
        AbstractDatabase.getInstance(context).cartDAO().delete(cart);
    }
    public class CartViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvQuantity;
        TextView tvPrice;
        //TextView tvToTalPrice;
        TextView tvPromotion;
        ImageView imgProduct;
        Button btnPlus;
        Button btnMinus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_cart_productname);
            tvQuantity = itemView.findViewById(R.id.tv_cartItem_quantity);
            tvPrice = itemView.findViewById(R.id.tv_cart_pricepromotion);
            tvPromotion = itemView.findViewById(R.id.tv_cart_price);
            //tvToTalPrice = itemView.findViewById(R.id.tv);
            imgProduct = itemView.findViewById(R.id.iv_cart_imageproduct);
            btnPlus = itemView.findViewById(R.id.btn_cartItem_plus);
            btnMinus = itemView.findViewById(R.id.btn_cartItem_minus);
        }
    }

}
