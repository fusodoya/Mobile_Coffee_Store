package com.example.coffeestore.Home;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.coffeestore.Home.Orders.Orders;
import com.example.coffeestore.Home.Reward.Reward;
import com.example.coffeestore.Home.Store.Store;
import com.example.coffeestore.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnApplyWindowInsetsListener(null);
        bottomNavigationView.setPadding(0, 0, 0, 0);

        // Get the data from Intent
        Bundle get_bundle = getIntent().getExtras();
        assert get_bundle != null;
        String nav_button = get_bundle.getString("button", "Store");
        // Load default fragment
        if (Objects.equals(nav_button, "Store")) {
            loadFragment(new Store());
            bottomNavigationView.setSelectedItemId(R.id.menu_store);
        }
        else if (Objects.equals(nav_button, "Reward")) {
            loadFragment(new Reward());
            bottomNavigationView.setSelectedItemId(R.id.menu_reward);
        }
        else if (Objects.equals(nav_button, "Orders")) {
            loadFragment(new Orders());
            bottomNavigationView.setSelectedItemId(R.id.menu_orders);
        }
        else {
            loadFragment(new Store());
            bottomNavigationView.setSelectedItemId(R.id.menu_store);
        }

        // Set listener for BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            int fragment_id = item.getItemId();
            if (fragment_id == R.id.menu_store) fragment = new Store();
            else if (fragment_id == R.id.menu_reward) fragment = new Reward();
            else if (fragment_id == R.id.menu_orders) fragment = new Orders();
            if (fragment != null) {
                loadFragment(fragment);
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_nav_host_fragment, fragment)
                .commit();
    }
}