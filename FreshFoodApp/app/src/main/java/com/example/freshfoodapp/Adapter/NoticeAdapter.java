package com.example.freshfoodapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.freshfoodapp.Models.Notification;
import com.example.freshfoodapp.ProductOfCategoryActivity;
import com.example.freshfoodapp.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    ArrayList<Notification> notifications;
    Context context;

    public NoticeAdapter(ArrayList<Notification> notifications, Context context) {
        this.notifications = notifications;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.ViewHolder holder, int position) {
        holder.title.setText(notifications.get(position).getTitle());
        holder.desc.setText(notifications.get(position).getDesc());

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(notifications.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imgNotice);

        String url = "";
        switch (position){

            case 0:
                url="https://www.bachhoaxanh.com/kinh-nghiem-hay/hoa-don-san-pham-knorr-tu-100k-tang-duong-mia-bien-hoa-500g-1529228";
                break;
            case 1:
                url="https://www.bachhoaxanh.com/kinh-nghiem-hay/ket-qua-quay-so-trung-thuong-ngay-05-05-khi-mua-san-pham-heineken-1529039";
                break;
            case 2:
                url="https://www.bachhoaxanh.com/kinh-nghiem-hay/tu-1-5-11-5-2023-thuc-pham-dong-mat-khuyen-mai-soc-1529174";
                break;
            case 3:
                url="https://www.bachhoaxanh.com/kinh-nghiem-hay/mua-ta-pampers-cao-cap-giam-den-30-tang-hop-thuy-tinh-locknlock-1529031";
                break;
            case 4:
                url="https://www.bachhoaxanh.com/kinh-nghiem-hay/tu-01-05-31-05-2023-pho-mai-bo-sua-chua-gia-chi-tu-20500d-1528835";
                break;
            case 5:
                url="https://www.bachhoaxanh.com/kinh-nghiem-hay/tu-01-05-31-05-2023-nuoc-mam-cac-loai-giam-den-50-1528822";
                break;
            case 6:
                url="https://www.bachhoaxanh.com/kinh-nghiem-hay/tu-28-4-11-5-2023-dau-an-meizan-1-lit-hat-nem-meizan-1-kg-dong-gia-45k-1527875";
                break;
        }
        String finalUrl = url;
        holder.itemNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(finalUrl));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, desc;
        ImageView imgNotice;

        ConstraintLayout itemNotice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_titleNotice);
            desc = itemView.findViewById(R.id.tv_descripNotice);
            imgNotice = itemView.findViewById(R.id.img_notify);
            itemNotice = itemView.findViewById(R.id.item_notice);
        }
    }
}
