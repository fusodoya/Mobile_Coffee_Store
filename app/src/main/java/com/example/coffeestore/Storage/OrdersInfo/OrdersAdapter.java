package com.example.coffeestore.Storage.OrdersInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeestore.R;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OnGoingViewHolder> {

    private Context context;
    private List<OrderItem> OnGoingItems;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public OrdersAdapter(Context context, List<OrderItem> OnGoingItems, OnItemClickListener listener) {
        this.context = context;
        this.OnGoingItems = OnGoingItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OnGoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OnGoingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnGoingViewHolder holder, int position) {
        OrderItem item = OnGoingItems.get(position);
        holder.txtDateTime.setText(item.getTime());
        holder.txtCoffeeName.setText(item.getCoffeeName());
        holder.txtAddress.setText(item.getAddress());
        holder.txtPrice.setText(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return OnGoingItems.size();
    }

    public static class OnGoingViewHolder extends RecyclerView.ViewHolder {
        TextView txtDateTime, txtCoffeeName, txtAddress, txtPrice;

        public OnGoingViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDateTime = itemView.findViewById(R.id.txtDateTime);
            txtCoffeeName = itemView.findViewById(R.id.txtCoffeeName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }
}
