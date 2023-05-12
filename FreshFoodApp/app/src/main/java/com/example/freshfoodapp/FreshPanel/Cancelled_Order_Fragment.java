package com.example.freshfoodapp.FreshPanel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.freshfoodapp.Adapter.OrderListViewAdapter;
import com.example.freshfoodapp.Models.OrderList;
import com.example.freshfoodapp.R;

import java.util.ArrayList;

public class Cancelled_Order_Fragment extends Fragment {
    private  String btnstt="Chi tiết đơn hủy";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_order, container, false);
        ListView listViewOrder = view.findViewById(R.id.listorder_confirm);
        ArrayList<OrderList> listOrder = new ArrayList<>();
        listOrder = new ArrayList<>();
        listOrder.add(new OrderList(1, 1000, "https://cdn.tgdd.vn/Products/Images/8788/275711/bhx/quyt-giong-uc-tui-1kg-5-9-trai-202205130905285767.jpg", "Quýt giống Úc ", 100000, 1 ));
        listOrder.add(new OrderList(2, 1000, "https://cdn.tgdd.vn/Products/Images/8782/273937/bhx/muc-ong-nguyen-con-khay-500g-10-13-con-202303010858110302.jpg", "Mực ống nguyên con", 25000, 1));
        listOrder.add(new OrderList(2, 1000, "https://cdn.tgdd.vn/Products/Images/8782/291428/bhx/ca-chim-trang-bien-nguyen-con-lam-sach-400g-600g-202303311326435364.jpg", "Cá chim trắng biển nguyên con làm sạch", 50000, 1 ));


//        OrderListViewAdapter orderListViewAdapter = new OrderListViewAdapter(container.getContext(), R.layout.order_view, listOrder);
        OrderListViewAdapter orderListViewAdapter = new OrderListViewAdapter(requireContext() , R.layout.order_view, listOrder, btnstt);
        listViewOrder.setAdapter(orderListViewAdapter);
        if(!listOrder.isEmpty())
        {
            TextView myTextView = view.findViewById(R.id.textview1);
            myTextView.setVisibility(View.GONE);
        }
        return view;
    }
}