package com.example.coffeestore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffeestore.Home.Home;
import com.example.coffeestore.Storage.CoffeeMenu.CoffeeInfo;
import com.example.coffeestore.Storage.CoffeeMenu.CoffeeMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        CoffeeMenu.list.add(new CoffeeInfo(R.drawable.americano, "Americano", 1.5f, 750));
        CoffeeMenu.list.add(new CoffeeInfo(R.drawable.cappuchino, "Cappuccino", 2f, 1000));
        CoffeeMenu.list.add(new CoffeeInfo(R.drawable.mocha, "Mocha", 2.5f, 1250));
        CoffeeMenu.list.add(new CoffeeInfo(R.drawable.flat_white, "Flat White", 2f, 1000));
        CoffeeMenu.list.add(new CoffeeInfo(R.drawable.espresso, "Espresso", 3f, 1500));
        CoffeeMenu.list.add(new CoffeeInfo(R.drawable.macchiato, "Macchiato", 2.5f, 1250));
        CoffeeMenu.list.add(new CoffeeInfo(R.drawable.latte, "Latte", 3f, 1500));

<<<<<<< HEAD
        // Chờ 2 giây (2000 ms), sau đó khởi chạy HomeActivity
=======

>>>>>>> f3a1f3e (build cleanning)
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, Home.class);
            Bundle bundle = new Bundle();
            bundle.putString("button", "Home");
            intent.putExtras(bundle);
            startActivity(intent);
<<<<<<< HEAD
            finish(); // Kết thúc MainActivity để không quay lại được
        }, 2000); // 2000 ms = 2 giây
=======
            finish();
        }, 2000);
>>>>>>> f3a1f3e (build cleanning)
    }
}