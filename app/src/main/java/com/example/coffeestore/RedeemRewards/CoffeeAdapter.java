package com.example.coffeestore.RedeemRewards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeestore.R;
import com.example.coffeestore.Storage.CoffeeMenu.CoffeeInfo;
import com.example.coffeestore.Storage.CoffeeMenu.CoffeeMenu;
import com.example.coffeestore.Storage.Reward.RewardItem;
import com.example.coffeestore.Storage.Reward.RewardList;
import com.example.coffeestore.Storage.Reward.RewardPoint;
import com.example.coffeestore.Storage.User.UserInfo;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder> {

    private Context context;
    private OnRedeemClickListener onRedeemClickListener; // Listener

    public interface OnRedeemClickListener {
        void onRedeemClick(CoffeeInfo coffeeInfo, boolean isRedeemable);
    }

    public CoffeeAdapter(Context context, OnRedeemClickListener listener) {
        this.context = context;
        this.onRedeemClickListener = listener;
    }

    @NonNull
    @Override
    public CoffeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_redeem, parent, false);
        return new CoffeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeViewHolder holder, int position) {
        CoffeeInfo coffee = CoffeeMenu.list.get(position);
        holder.coffeeName.setText(coffee.getName());
        holder.coffeeValidUntil.setText("Valid until 04.07.21");
        holder.coffeePoints.setText(coffee.getPtsRedeem() + " pts");
        holder.coffeeImage.setImageResource(coffee.getImgID());

        // Xử lý nhấn vào item
        holder.itemView.setOnClickListener(v -> {
            boolean isRedeemable = RewardPoint.point >= coffee.getPtsRedeem();
            if (isRedeemable) {
                RewardPoint.point -= coffee.getPtsRedeem();
                RewardList.list.addFirst(new RewardItem(
                        "Redeem " + coffee.getName(),
                        UserInfo.getTimeDay(),
                        -coffee.getPtsRedeem()
                ));
            }
            onRedeemClickListener.onRedeemClick(coffee, isRedeemable);
        });
    }

    @Override
    public int getItemCount() {
        return CoffeeMenu.list.size();
    }

    public static class CoffeeViewHolder extends RecyclerView.ViewHolder {
        ImageView coffeeImage;
        TextView coffeeName, coffeeValidUntil, coffeePoints;

        public CoffeeViewHolder(@NonNull View itemView) {
            super(itemView);
            coffeeImage = itemView.findViewById(R.id.redeem_coffee_image);
            coffeeName = itemView.findViewById(R.id.redeem_coffee_name);
            coffeeValidUntil = itemView.findViewById(R.id.redeem_coffee_valid_until);
            coffeePoints = itemView.findViewById(R.id.redeem_coffee_points);
        }
    }
}
