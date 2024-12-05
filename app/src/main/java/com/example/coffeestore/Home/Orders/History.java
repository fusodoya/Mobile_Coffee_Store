package com.example.coffeestore.Home.Orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeestore.R;
import com.example.coffeestore.Storage.OrdersInfo.HistoryList;
import com.example.coffeestore.Storage.OrdersInfo.OrdersAdapter;

import org.jetbrains.annotations.Nullable;

public class History extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout của fragment
        return inflater.inflate(R.layout.fragment_orders_statement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewOrders;
        recyclerViewOrders = view.findViewById(R.id.recyclerViewOrder);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(requireContext()));

        OrdersAdapter adapter = new OrdersAdapter(requireContext(), HistoryList.list, position -> {});
        recyclerViewOrders.setAdapter(adapter);
    }
}