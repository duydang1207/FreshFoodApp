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
        notices.add(new Notification("Từ 1/5 - 31/5/2023, hóa đơn sản phẩm Knorr từ 100k tặng Đường Mía Biên Hòa 500g","knorr","Từ 01/05 - 31/05/2023, quý khách hàng mua sản phẩm Knorr với hoá đơn từ 100k được tặng ngay đường mía Biên Hòa gói 500g. Nhanh tay mua ngay, số lượng có hạn!"));
        notices.add(new Notification("Kết quả quay số trúng thưởng ngày 05/05 khi mua sản phẩm Heineken","heineken","Chương trình quay số trúng thưởng khi mua sản phẩm của Heineken tại Bách hóa XANH đã có kết quả khách hàng quay số trúng thưởng ngày 5/5."));
        notices.add(new Notification("Từ 1/5 - 11/5/2023, thực phẩm đông mát khuyến mãi sốc","tpdong","Từ 1/5 - 11/5/2023, khi quý khách hàng đặt mua thực phẩm đông mát tại Bách hóa XANH sẽ nhận được khuyến mãi sốc. Nhanh tay đặt hàng ngay!"));
        notices.add(new Notification("Mua tã Pampers cao cấp giảm đến 30%, tặng hộp thủy tinh LocknLock","pamper","Từ 1/5 - 31/5/2023, khi quý khách hàng đặt mua tã Pampers cao cấp tại Bách hóa XANH sẽ nhận được ưu đãi giảm đến 30% và có cơ hội nhận quà tặng là hộp thủy tinh LocknLock."));
        notices.add(new Notification("Từ 01/05 - 31/05/2023, phô mai bơ sữa chua giá chỉ từ 20.500đ","phomai","Từ ngày 01/05 - 31/05/2023, chọn mua phô mai, bơ, sữa chua các loại chính hãng, chất lượng tại Bách hoá XANH giá chỉ từ 20.500đ. Nhanh tay đặt hàng ngay!"));
        notices.add(new Notification("Từ 01/05 - 31/05/2023, nước mắm các loại giảm đến 50%","nuocmam","Từ ngày 01/05 - 31/05/2023, chọn mua nước mắm các loại chính hãng, chất lượng tại Bách hoá XANH được giảm giá cực \"hời\" lên đến 50%. Nhanh tay đặt hàng ngay!"));
        notices.add(new Notification("Từ 28/4 - 11/5/2023, dầu ăn Meizan 1 lít, hạt nêm Meizan 1 kg đồng giá 45k","dauan","Từ 28/4 - 11/5/2023, khi quý khách hàng đặt hàng chai dầu ăn Meizan Gold 1 lít hoặc gói hạt nêm Meizan 1 kg sẽ được mua với giá 45.000 đồng. Nhanh tay đặt hàng ngay nhé!"));

        adapter = new NoticeAdapter(notices, getContext());
        rvNotification.setAdapter(adapter);
    }
}
