package com.example.coffeestore.Storage.Reward;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeestore.R;

import java.util.List;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.RewardViewHolder> {

    private List<RewardItem> rewardItemList;

    // Constructor
    public RewardAdapter(List<RewardItem> rewardItemList) {
        this.rewardItemList = rewardItemList;
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reward, parent, false);
        return new RewardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {
        RewardItem item = rewardItemList.get(position);

        holder.rewardName.setText(item.getName());
        holder.rewardDayTime.setText(item.getDayTime());
        if (item.getPoint() > 0) {
            holder.rewardPoints.setText("+ " + item.getPoint() + " Pts");
        } else {
            holder.rewardPoints.setText("- " + (-item.getPoint()) + " Pts");
        }
    }

    @Override
    public int getItemCount() {
        return rewardItemList.size();
    }

    public static class RewardViewHolder extends RecyclerView.ViewHolder {
        TextView rewardName, rewardDayTime, rewardPoints;

        public RewardViewHolder(@NonNull View itemView) {
            super(itemView);
            rewardName = itemView.findViewById(R.id.item_reward_name);
            rewardDayTime = itemView.findViewById(R.id.item_reward_time);
            rewardPoints = itemView.findViewById(R.id.item_reward_point);
        }
    }
}
