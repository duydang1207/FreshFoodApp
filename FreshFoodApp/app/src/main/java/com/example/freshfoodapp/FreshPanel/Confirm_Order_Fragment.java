package com.example.freshfoodapp.FreshPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.freshfoodapp.Adapter.OrderListViewAdapter;
import com.example.freshfoodapp.Models.OrderList;
import com.example.freshfoodapp.R;

import java.util.ArrayList;

public class Confirm_Order_Fragment extends Fragment {
    private  String mfragment ="confirm";
    Button btn;
    private Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_order, container, false);
        ListView listViewOrder = view.findViewById(R.id.listorder_confirm);
        ArrayList<OrderList> listOrder = new ArrayList<>();
        listOrder = new ArrayList<>();
        listOrder = new ArrayList<>();

//        OrderListViewAdapter orderListViewAdapter = new OrderListViewAdapter(container.getContext(), R.layout.order_view, listOrder);
        OrderListViewAdapter orderListViewAdapter = new OrderListViewAdapter(requireContext() , R.layout.order_view, listOrder, mfragment);
        listViewOrder.setAdapter(orderListViewAdapter);
        if(!listOrder.isEmpty())
        {

            LinearLayout checkorder = view.findViewById(R.id.empty_order);
            checkorder.setVisibility(View.GONE);
        }
        return view;

    }
    public  void abc()
    {
        Toast.makeText(getActivity()    , "confirm", Toast.LENGTH_SHORT).show();
    }
}