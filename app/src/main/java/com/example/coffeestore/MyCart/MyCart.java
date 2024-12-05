package com.example.coffeestore.MyCart;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeestore.Home.Home;
import com.example.coffeestore.OrderSuccess.OrderSuccess;
import com.example.coffeestore.R;
import com.example.coffeestore.Storage.Cart.CartAdapter;
import com.example.coffeestore.Storage.Cart.CartItem;
import com.example.coffeestore.Storage.Cart.CartList;
import com.example.coffeestore.Storage.OrdersInfo.OnGoingList;
import com.example.coffeestore.Storage.OrdersInfo.OrderItem;
import com.example.coffeestore.Storage.Reward.RewardItem;
import com.example.coffeestore.Storage.Reward.RewardList;
import com.example.coffeestore.Storage.Reward.RewardPoint;
import com.example.coffeestore.Storage.User.UserInfo;

import java.util.Objects;

public class MyCart extends AppCompatActivity {
    private TextView tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_my_cart), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView my_cart_back = findViewById(R.id.my_cart_back);
        my_cart_back.setOnClickListener(view -> {
            Bundle get_bundle = getIntent().getExtras();
            assert get_bundle != null;
            boolean is_return = get_bundle.getBoolean("is_return", false);
            if (is_return) {
                finish();
            } else {
                String activity_name = get_bundle.getString("activity", "Home");
                if (Objects.equals(activity_name, "Home")) {
                    String button = get_bundle.getString("button", "Store");

                    Intent intent = new Intent(MyCart.this, Home.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("button", button);
                    intent.putExtras(bundle);

                    startActivity(intent);
                    finish();
                }
            }
        });

        RecyclerView recyclerViewCart;
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        CartAdapter adapter = new CartAdapter(this, CartList.list, position -> {});
        recyclerViewCart.setAdapter(adapter);

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
                CartList.list.remove(position);
                adapter.notifyItemRemoved(position);
                updateTotalPrice();
            }
        }).attachToRecyclerView(recyclerViewCart);

        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        updateTotalPrice();

        Button btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(v -> {

            String dayTime = UserInfo.getTimeDay();
            for (CartItem item : CartList.list) {
                RewardPoint.cupCnt += item.getQuantity();
                RewardPoint.point += (int) (item.getCost() * RewardPoint.moneyToPts);

                OnGoingList.list.addFirst(new OrderItem(
                        dayTime,
                        item.getName(),
                        UserInfo.getAddress(),
                        item.getPrice()
                ));

                RewardList.list.addFirst(new RewardItem(
                        item.getName(),
                        dayTime,
                        (int) (item.getCost() * RewardPoint.moneyToPts)
                ));
            }
            CartList.list.clear();

            Intent intent = new Intent(MyCart.this, OrderSuccess.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

    }

    private void updateTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem item : CartList.list) {
            totalPrice += item.getCost();
        }
        tvTotalPrice.setText("Total Price: $" + String.format("%.2f", totalPrice));
    }
}