package com.example.freshfoodapp.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Models.Cart;
import com.example.freshfoodapp.R;

import java.math.BigDecimal;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Cart> carts;

    public CartAdapter(Context context, int layout, List<Cart> carts) {
        this.context = context;
        this.layout = layout;
        this.carts = carts;
    }


    @Override
    public int getCount() {
        return carts==null?0:carts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //lấy context
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //gọi view chứa layout
        view = inflater.inflate(layout,null);
        //ánh xạ view
        TextView textName = (TextView) view.findViewById(R.id.tv_cart_productname);
        TextView textPrice = (TextView) view.findViewById(R.id.tv_cart_price);
        ImageView image = (ImageView) view.findViewById(R.id.iv_cart_imageproduct);
        TextView textPricePromotion = (TextView) view.findViewById(R.id.tv_cart_pricepromotion);
        TextView textQuantity = (TextView) view.findViewById(R.id.tv_cartItem_quantity);
        Button btnPlus = (Button) view.findViewById(R.id.btn_cartItem_plus);
        Button btnMinus = (Button) view.findViewById(R.id.btn_cartItem_minus);
//        TextView tvSale = (TextView) view.findViewById(R.id.iv_cart_sale);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_temp = Integer.parseInt(textQuantity.getText().toString()) + 1;
                textQuantity.setText(String.valueOf(quantity_temp));
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity_temp = Integer.parseInt(String.valueOf(textQuantity.getText().toString()));

                if(quantity_temp > 1){
                    quantity_temp--;
                    textQuantity.setText(String.valueOf(quantity_temp));
                }
                else
                    Toast.makeText(context.getApplicationContext(),"Đã đạt số lượng tối thiểu",Toast.LENGTH_SHORT).show();
            }
        });

        //gan gia tri
        Cart cart = carts.get(i);
        textName.setText(cart.getName());
        if(cart.getPromotion() == 0){
            textPrice.setText(String.valueOf(cart.getPrice().toString())+"đ");
            textPricePromotion.setText(String.valueOf(""));
//            tvSale.setBackground(null);
        }
        else {
            textPricePromotion.setText(String.valueOf(cart.getPrice()));
            BigDecimal price = cart.getPrice().subtract(cart.getPrice().multiply(BigDecimal.valueOf(cart.getPromotion()).divide(BigDecimal.valueOf(100))));
            textPrice.setText(String.valueOf(price)+" đ");
            textPricePromotion.setPaintFlags(textPricePromotion.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        }
        textQuantity.setText(String.valueOf(cart.getQuantity()));
        Glide.with(context).load(cart.getImage()).into(image);
        image.setScaleType(ImageView.ScaleType.FIT_XY);

        //trả về view
        return view;
    }
}
