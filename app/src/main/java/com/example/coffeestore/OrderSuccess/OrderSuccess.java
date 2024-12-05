package com.example.coffeestore.OrderSuccess;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffeestore.Home.Home;
import com.example.coffeestore.MyCart.MyCart;
import com.example.coffeestore.R;

public class OrderSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_success);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_order_success), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnCheckout = findViewById(R.id.track_my_order_button);
        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(OrderSuccess.this, Home.class);
            Bundle bundle = new Bundle();
            bundle.putString("button", "Orders");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
    }
}