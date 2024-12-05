package com.example.coffeestore.Home.CommonFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeestore.Home.Home;
import com.example.coffeestore.R;
import com.example.coffeestore.Storage.Reward.RewardItem;
import com.example.coffeestore.Storage.Reward.RewardList;
import com.example.coffeestore.Storage.Reward.RewardPoint;
import com.example.coffeestore.Storage.User.UserInfo;

import org.jetbrains.annotations.Nullable;

public class LoyaltyCard extends Fragment {
    private final Context context;
    private ImageView[] coffeeCups;
    String button;

    public LoyaltyCard(Context context, String button) {
        this.context = context;
        this.button = button;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout của fragment
        return inflater.inflate(R.layout.fragment_loyalty_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Liên kết các ImageView
        coffeeCups = new ImageView[]{
                view.findViewById(R.id.coffee01),
                view.findViewById(R.id.coffee02),
                view.findViewById(R.id.coffee03),
                view.findViewById(R.id.coffee04),
                view.findViewById(R.id.coffee05),
                view.findViewById(R.id.coffee06),
                view.findViewById(R.id.coffee07),
                view.findViewById(R.id.coffee08)
        };
        updateCoffeeCups(view, RewardPoint.cupCnt);


        TextView claimOneButton = view.findViewById(R.id.reward_claim_one);
        TextView claimAllButton = view.findViewById(R.id.reward_claim_all);

        // Xử lý sự kiện khi nhấn nút "Claim One"
        claimOneButton.setOnClickListener(v -> {
            int cnt = RewardPoint.cupCnt;

            if (cnt >= 8) {
                RewardPoint.cupCnt -= 8;
                RewardPoint.point += RewardPoint.cardValue;

                RewardList.list.addFirst(new RewardItem(
                        "Claim one card",
                        UserInfo.getTimeDay(),
                        RewardPoint.cardValue
                ));

                resetActivity();
            }
        });

        // Xử lý sự kiện khi nhấn nút "Claim All"
        claimAllButton.setOnClickListener(v -> {
            int cnt = RewardPoint.cupCnt;

            if (cnt >= 8) {
                int numCard = (int) (cnt / 8);

                RewardPoint.cupCnt -= numCard * 8;
                RewardPoint.point += numCard * RewardPoint.cardValue;

                RewardList.list.addFirst(new RewardItem(
                        "Claim all card",
                        UserInfo.getTimeDay(),
                        numCard * RewardPoint.cardValue
                ));

                resetActivity();
            }
        });
    }

    private void resetActivity() {
        Intent intent = new Intent(context, Home.class); // Activity bạn muốn mở
        Bundle bundle = new Bundle();
        bundle.putString("button", button);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    private void updateCoffeeCups(View view, int cnt) {
        if (cnt > 8) cnt = 8;

        for (int i = 0; i < coffeeCups.length; i++) {
            if (i < cnt) {
                coffeeCups[i].setImageResource(R.drawable.coffee_filled);
            } else {
                coffeeCups[i].setImageResource(R.drawable.coffee_empty);
            }
        }

        TextView coffee_count = view.findViewById(R.id.coffee_bought);
        coffee_count.setText(cnt + " / " + coffeeCups.length);
    }
}