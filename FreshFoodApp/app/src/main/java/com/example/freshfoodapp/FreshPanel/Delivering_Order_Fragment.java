package com.example.freshfoodapp.FreshPanel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.freshfoodapp.Adapter.OrderListViewAdapter;
import com.example.freshfoodapp.Models.OrderList;
import com.example.freshfoodapp.R;

import java.util.ArrayList;


public class Delivering_Order_Fragment extends Fragment {
    private String btnstt="Xác nhận đã nhận";
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_order, container, false);
        ListView listViewOrder = view.findViewById(R.id.listorder_confirm);
//        View view = inflater.inflate(R.layout.order_view, container, false);
//
//        btn = (Button) view.findViewById(R.id.btn_order_list);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(requireContext(), "abc", Toast.LENGTH_SHORT).show();
//            }
//        });

        ArrayList<OrderList> listOrder = new ArrayList<>();
        listOrder = new ArrayList<>();
        listOrder.add(new OrderList(1, 1000, "https://cdn.tgdd.vn/Products/Images/8139/304177/bhx/bo-xay-fohla-250g-202303251515163566.jpg", "Bò xay Fohla 250g", 1000, 1 ));
        listOrder.add(new OrderList(2, 1000, "https://cdn.tgdd.vn/Products/Images/8781/242942/bhx/thit-heo-xay-khay-500g-202111262116093264.jpg", "Thịt heo xay C.P 500g", 1000, 1 ));
        listOrder.add(new OrderList(1, 1000, "https://cdn.tgdd.vn/Products/Images/8139/304177/bhx/bo-xay-fohla-250g-202303251515163566.jpg", "Bò xay Fohla 250g", 1000, 1  ));
        listOrder.add(new OrderList(2, 1000, "https://cdn.tgdd.vn/Products/Images/8781/242942/bhx/thit-heo-xay-khay-500g-202111262116093264.jpg", "Thịt heo xay C.P 500g", 1000, 1 ));

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