package com.example.freshfoodapp.Adapter.ThongKe;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
import com.androidplot.ui.Size;
import com.example.freshfoodapp.R;

import java.math.BigDecimal;

public class RevenuAdapter extends RecyclerView.Adapter<RevenuAdapter.ViewHolder> {
    Context context;
    Double total,totalByMonth;
    Segment s1,s2;

    public RevenuAdapter(Context context, Double total, Double totalByMonth) {
        this.context = context;
        this.total = total;
        this.totalByMonth = totalByMonth;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        PieChart pieChart;
        TextView total,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pieChart = itemView.findViewById(R.id.pieChart);
            total = itemView.findViewById(R.id.tv_thongke_doanhthu_total);
            price = itemView.findViewById(R.id.tv_thongke_doanhthu_price);
        }
    }
    @NonNull
    @Override
    public RevenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_thongke_doanhthu,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RevenuAdapter.ViewHolder holder, int position) {
        s1 = new Segment("Tháng("+String.valueOf(totalByMonth)+")", totalByMonth.doubleValue());
        s2 = new Segment(String.valueOf(total),total);
        SegmentFormatter sf1 = new SegmentFormatter(Color.BLUE);
        SegmentFormatter sf2 = new SegmentFormatter(Color.RED);
        holder.pieChart.addSegment(s1,sf1);
        holder.pieChart.addSegment(s2,sf2);

        holder.price.setText("Doanh thu tháng này là: đ"+String.valueOf(totalByMonth));
        holder.total.setText("Doanh thu cả cửa hàng là: đ"+String.valueOf(total));

    }

    @Override
    public int getItemCount() {
       return 1;
    }
}
