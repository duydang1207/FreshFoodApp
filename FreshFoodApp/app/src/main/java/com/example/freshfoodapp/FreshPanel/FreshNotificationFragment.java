package com.example.freshfoodapp.FreshPanel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfoodapp.Adapter.NoticeAdapter;
import com.example.freshfoodapp.Models.Notification;
import com.example.freshfoodapp.R;

import java.util.ArrayList;
import java.util.List;

public class FreshNotificationFragment extends Fragment {
    private RecyclerView rvNotification;
    private RecyclerView.Adapter adapter;
    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fresh_notification, null);

        recycleNotifications();
        return v;
    }

    private void recycleNotifications(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvNotification = v.findViewById(R.id.rv_notification);
        rvNotification.setLayoutManager(linearLayoutManager);

        ArrayList<Notification> notices = new ArrayList<>();
        notices.add(new Notification("Tã bỉm giảm đến 45%, hoá đơn từ 500k tặng quà ngẫu nhiên","meat","Từ ngày 01/05 - 31/05/2023, tã bỉm các loại giảm đến 45%, hoá đơn từ 500k tặng quà ngẫu nhiên vô cùng hấp dẫn từ Bách hoá XANH. Đặt hàng ngay, số lượng có hạn!"));
        notices.add(new Notification("Từ 1/5 - 11/5/2023, thực phẩm đông mát khuyến mãi sốc","fish","Từ 1/5 - 11/5/2023, khi quý khách hàng đặt mua thực phẩm đông mát tại Bách hóa XANH sẽ nhận được khuyến mãi sốc. Nhanh tay đặt hàng ngay!"));
        notices.add(new Notification("Chương trình giao hàng miễn phí","free_ship",""));
        notices.add(new Notification("Chào mừng đại lễ 30-4","fish_ngu","Từ 01/05 - 31/05/2023, quý khách hàng mua sản phẩm Knorr với hoá đơn từ 100k được tặng ngay đường mía Biên Hòa gói 500g. Nhanh tay mua ngay, số lượng có hạn!"));
        notices.add(new Notification("Từ 01/05 - 31/05/2023, nước mắm các loại giảm đến 50%","meat_beef","Từ ngày 01/05 - 31/05/2023, chọn mua nước mắm các loại chính hãng, chất lượng tại Bách hoá XANH được giảm giá cực \"hời\" lên đến 50%. Nhanh tay đặt hàng ngay!"));
        notices.add(new Notification("Kết quả quay số trúng thưởng ngày 21/04 khi mua sản phẩm của Pepsi","promotion","Chương trình quay số trúng thưởng khi mua sản phẩm của Pepsi tại Bách hóa XANH đã có kết quả khách hàng quay số trúng thưởng ngày 21/4."));
        notices.add(new Notification("Từ 01/05 - 31/05/2023, đồ dùng gia đình các loại giảm đến 49%","meat","Từ 01/05 - 31/05/2023, Bách hoá XANH diễn ra chương trình khuyến mãi hấp dẫn, đồ dùng gia đình các loại giảm sâu đến 49%. Nhanh tay đặt hàng, số lượng có hạn!"));

        adapter = new NoticeAdapter(notices, getContext());
        rvNotification.setAdapter(adapter);
    }
}
