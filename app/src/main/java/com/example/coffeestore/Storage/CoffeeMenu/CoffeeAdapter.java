package com.example.coffeestore.Storage.CoffeeMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeestore.Details.Details;
import com.example.coffeestore.R;

import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    private final Context context;
    private final List<CoffeeInfo> coffeeList;

    public CoffeeAdapter(Context context, List<CoffeeInfo> coffeeList) {
        this.context = context;
        this.coffeeList = coffeeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_coffee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoffeeInfo coffee = coffeeList.get(position);
        holder.nameTextView.setText(coffee.getName());
        holder.imageView.setImageResource(coffee.getImgID());

        // Xử lý sự kiện khi nhấn vào item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Details.class); // Activity bạn muốn mở
            Bundle bundle = new Bundle();
            bundle.putInt("imgID", coffee.getImgID());
            bundle.putString("name", coffee.getName());
            bundle.putDouble("price", coffee.getPrice());
            intent.putExtras(bundle);
            context.startActivity(intent);
            ((Activity) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return coffeeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_coffee_image);
            nameTextView = itemView.findViewById(R.id.item_coffee_text);
        }
    }
}
