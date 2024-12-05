package com.example.coffeestore.Home.Orders;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coffeestore.R;
import com.example.coffeestore.Storage.OrdersInfo.HistoryList;
import com.example.coffeestore.Storage.OrdersInfo.OrdersAdapter;
import com.example.coffeestore.Storage.OrdersInfo.OnGoingList;

import org.jetbrains.annotations.Nullable;

public class OnGoing extends Fragment {
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

        OrdersAdapter adapter = new OrdersAdapter(requireContext(), OnGoingList.list, position -> {});
        recyclerViewOrders.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                final float alpha = 1.0f - Math.abs(dX) / recyclerView.getWidth();
                viewHolder.itemView.setAlpha(alpha);
                viewHolder.itemView.setTranslationX(dX);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                HistoryList.list.addFirst(OnGoingList.list.get(position));
                OnGoingList.list.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(recyclerViewOrders);
    }
}