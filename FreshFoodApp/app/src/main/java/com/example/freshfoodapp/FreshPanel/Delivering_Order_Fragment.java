package com.example.freshfoodapp.FreshPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.freshfoodapp.Adapter.OrderListViewAdapter;
import com.example.freshfoodapp.Models.OrderList;
import com.example.freshfoodapp.R;

import java.util.ArrayList;


public class Delivering_Order_Fragment extends Fragment {
    private String mfragment ="delivering";
    Button btn;
    private Fragment fragment;
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
        listOrder.add(new OrderList(1, 1000, "https://cdn.tgdd.vn/Products/Images/8139/304177/bhx/bo-xay-fohla-250g-202303251515163566.jpg", "Bò xay Fohla 250g", 1000, 1 ));
        listOrder.add(new OrderList(2, 1000, "https://cdn.tgdd.vn/Products/Images/8781/242942/bhx/thit-heo-xay-khay-500g-202111262116093264.jpg", "Thịt heo xay C.P 500g", 1000, 1 ));
        listOrder.add(new OrderList(1, 1000, "https://cdn.tgdd.vn/Products/Images/8139/304177/bhx/bo-xay-fohla-250g-202303251515163566.jpg", "Bò xay Fohla 250g", 1000, 1  ));
        listOrder.add(new OrderList(2, 1000, "https://cdn.tgdd.vn/Products/Images/8781/242942/bhx/thit-heo-xay-khay-500g-202111262116093264.jpg", "Thịt heo xay C.P 500g", 1000, 1 ));

        OrderListViewAdapter orderListViewAdapter = new OrderListViewAdapter(requireContext() , R.layout.order_view, listOrder, mfragment);
        listViewOrder.setAdapter(orderListViewAdapter);
        if(!listOrder.isEmpty())
        {
            LinearLayout checkorder = view.findViewById(R.id.empty_order);
            checkorder.setVisibility(View.GONE);
        }

        return view;
    }
}