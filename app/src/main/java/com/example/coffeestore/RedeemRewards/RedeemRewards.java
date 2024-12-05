package com.example.coffeestore.RedeemRewards;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeestore.Home.Home;
<<<<<<< HEAD
import com.example.coffeestore.MainActivity;
=======
>>>>>>> f3a1f3e (build cleanning)
import com.example.coffeestore.R;
import com.example.coffeestore.Storage.OrdersInfo.OnGoingList;
import com.example.coffeestore.Storage.OrdersInfo.OrderItem;
import com.example.coffeestore.Storage.User.UserInfo;

public class RedeemRewards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_redeem_rewards);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_redeem_rewards), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView my_cart_back = findViewById(R.id.redeem_back_button);
        my_cart_back.setOnClickListener(view -> {
            Intent intent = new Intent(RedeemRewards.this, Home.class);
            Bundle bundle = new Bundle();
            bundle.putString("button", "Reward");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });

        RecyclerView recyclerView = findViewById(R.id.redeem_recyclerView); // Cập nhật ID
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

<<<<<<< HEAD
        CoffeeAdapter adapter = new CoffeeAdapter(this, (coffeeInfo, isRedeemable) -> {
=======
        RedeemAdapter adapter = new RedeemAdapter(this, (coffeeInfo, isRedeemable) -> {
>>>>>>> f3a1f3e (build cleanning)
            if (isRedeemable) {
                showAlertDialog("Redeem successful", "You have successfully redeemed your " + coffeeInfo.getName() + ". \nGo to the Orders to view the information.");
                OnGoingList.list.addFirst(new OrderItem(
                        UserInfo.getTimeDay(),
                        coffeeInfo.getName(),
                        UserInfo.getAddress(),
                        "$0"
                ));
            } else {
                showAlertDialog("Redeem failed", "You do not have enough points to redeem " + coffeeInfo.getName() + ".");
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

}