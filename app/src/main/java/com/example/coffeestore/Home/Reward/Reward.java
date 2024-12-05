package com.example.coffeestore.Home.Reward;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coffeestore.Home.CommonFragment.LoyaltyCard;
import com.example.coffeestore.MyCart.MyCart;
import com.example.coffeestore.R;
import com.example.coffeestore.RedeemRewards.RedeemRewards;
import com.example.coffeestore.Storage.Reward.RewardAdapter;
import com.example.coffeestore.Storage.Reward.RewardList;
import com.example.coffeestore.Storage.Reward.RewardPoint;

import org.jetbrains.annotations.Nullable;

public class Reward extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout của fragment
        return inflater.inflate(R.layout.fragment_reward, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.loyalty_card_fragment_container, new LoyaltyCard(requireContext(), "Reward"))
                .commit();


        RecyclerView recyclerViewOrders;
        recyclerViewOrders = view.findViewById(R.id.recyclerViewReward);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(requireContext()));

        RewardAdapter adapter = new RewardAdapter(RewardList.list);
        recyclerViewOrders.setAdapter(adapter);

        TextView point = view.findViewById(R.id.reward_point);
        point.setText(Integer.toString(RewardPoint.point));

        TextView redeem_drinks = view.findViewById(R.id.redeem_drinks);
        redeem_drinks.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext(), RedeemRewards.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
            ((Activity) requireContext()).finish();
        });
    }
}