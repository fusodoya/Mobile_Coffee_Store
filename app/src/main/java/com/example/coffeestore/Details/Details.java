package com.example.coffeestore.Details;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.PorterDuff;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffeestore.Home.Home;
import com.example.coffeestore.MyCart.MyCart;
import com.example.coffeestore.R;
import com.example.coffeestore.Storage.Cart.CartItem;
import com.example.coffeestore.Storage.Cart.CartList;

import java.util.concurrent.atomic.AtomicReference;

public class Details extends AppCompatActivity {
    private int cnt = 1;
    private ImageView[][] items = new ImageView[4][];
    private String[][] names = new String[4][];
    double coffee_cost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_details), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the data from Intent
        Bundle get_bundle = getIntent().getExtras();
        String coffee_name = get_bundle.getString("name", "Latte");
        int coffee_img_id = get_bundle.getInt("imgID", R.drawable.latte);
        double med_coffee_cost = get_bundle.getDouble("price", 3f);
        coffee_cost = 0.8 * med_coffee_cost;
        // Update UI
        TextView coffeeNameTextView = findViewById(R.id.detail_coffee_name);
        ImageView coffeeImgUrl = findViewById(R.id.detail_coffee_id);

        coffeeNameTextView.setText(coffee_name);
        coffeeImgUrl.setImageResource(coffee_img_id);

        ImageButton backArrow = findViewById(R.id.detail_back);
        backArrow.setOnClickListener(v->{
            Intent intent = new Intent(Details.this, Home.class);
            Bundle bundle = new Bundle();
            bundle.putString("button", "Store");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });

        ImageView detail_image = findViewById(R.id.detail_coffee_id);
        GradientDrawable detail_image_drawable = (GradientDrawable) detail_image.getBackground();
        detail_image_drawable.setColor(ContextCompat.getColor(this, R.color.light2_gray));

        ImageView detail_cart = findViewById(R.id.detail_cart);
        detail_cart.setOnClickListener(view -> {
            Intent intent = new Intent(Details.this, MyCart.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("is_return", true);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        cnt = 1;

        TextView detail_number_cup = findViewById(R.id.detail_number_cup);
        TextView detail_cost = findViewById(R.id.detail_cost);
        detail_cost.setText("$" + String.format("%.2f", coffee_cost * cnt));

        detail_number_cup.setOnClickListener(view -> {
            showEditDialog("Enter the number of cups of coffee: ", detail_number_cup);
        });

        ImageButton detail_minus = findViewById(R.id.detail_minus);
        detail_minus.setOnClickListener(v->{
            if (cnt > 1) --cnt;
            detail_number_cup.setText(Integer.toString(cnt));
            detail_cost.setText("$" + String.format("%.2f", coffee_cost * cnt));
        });

        ImageButton detail_plus = findViewById(R.id.detail_plus);
        detail_plus.setOnClickListener(v->{
            cnt++;
            detail_number_cup.setText(Integer.toString(cnt));
            detail_cost.setText("$" + String.format("%.2f", coffee_cost * cnt));
        });

        items[0] = new ImageView[] {
                findViewById(R.id.detail_single_shot),
                findViewById(R.id.detail_double_shot)
        };
        items[1] = new ImageView[] {
                findViewById(R.id.detail_hot),
                findViewById(R.id.detail_cold)
        };
        items[2] = new ImageView[] {
                findViewById(R.id.detail_small),
                findViewById(R.id.detail_medium),
                findViewById(R.id.detail_big)
        };
        items[3] = new ImageView[] {
                findViewById(R.id.detail_one),
                findViewById(R.id.detail_two),
                findViewById(R.id.detail_three)
        };

        names[0] = new String[] {
                "single",
                "double"
        };
        names[1] = new String[] {
                "hot",
                "iced"
        };
        names[2] = new String[] {
                "small",
                "medium",
                "big"
        };
        names[3] = new String[] {
                "no ice",
                "half ice",
                "full ice"
        };

        int types[] = new int[4];
        for (int i = 0; i < 4; ++i) types[i] = 0;
        updateDetail(types);

        for (int i = 0; i < 4; ++i) {
            int finalI = i;
            for (int j = 0; j < items[i].length; ++j) {
                int finalJ = j;
                items[i][j].setOnClickListener(view -> {
                    types[finalI] = finalJ;
                    if (types[1] == 0) types[3] = 0;

                    coffee_cost = (med_coffee_cost * (0.8 + 0.2 * types[2]) * (types[0] + 1));
                    detail_cost.setText("$" + String.format("%.2f", coffee_cost * cnt));

                    updateDetail(types);
                });
            }
        }

        LinearLayout detail_add_to_cart = findViewById(R.id.detail_add_to_cart);
        GradientDrawable detail_add_to_cart_drawble = (GradientDrawable) detail_add_to_cart.getBackground();
        detail_add_to_cart_drawble.setColor(ContextCompat.getColor(this, R.color.dark3_blue));

        detail_add_to_cart.setOnClickListener(view -> {
            String coffee_detail = "";
            for (int i = 0; i < 4; ++i) {
                if (i > 0) coffee_detail = coffee_detail + " | ";
                coffee_detail = coffee_detail + names[i][types[i]];
            }
            CartList.list.add(new CartItem(
                    coffee_name,
                    coffee_detail,
                    "$" + String.format("%.2f", coffee_cost * cnt),
                    cnt,
                    coffee_img_id,
                    coffee_cost * cnt
                    ));

            // Cart activity
            Intent intent = new Intent(Details.this, MyCart.class);
            Bundle bundle = new Bundle();
            bundle.putString("activity", "Home");
            bundle.putString("button", "Store");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
    }

    public void updateDetail(int[] types) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < items[i].length; ++j) {
                int color;
                if (j == types[i]) {
                    color = ContextCompat.getColor(this, R.color.dark3_blue);
                } else {
                    color = ContextCompat.getColor(this, R.color.light2_gray);
                }

                items[i][j].setColorFilter(color, PorterDuff.Mode.SRC_IN); // Chỉ thay đổi phần có màu của hình ảnh
            }
        }
    }

    // Hàm hiển thị hộp thoại chỉnh sửa thông tin
    private void showEditDialog(String title, TextView textView) {
        showEditDialog(title, textView, InputType.TYPE_CLASS_TEXT);
    }

    private void showEditDialog(String title, TextView textView, int inputType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        final EditText input = new EditText(this);
        input.setInputType(inputType);
        input.setText(textView.getText().toString());
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newValue = input.getText().toString().trim();

            if (!newValue.isEmpty()) {
                int number = Integer.parseInt(newValue);
                if (number > 0) {
                    cnt = number;
                    textView.setText(Integer.toString(cnt));

                    TextView detail_cost = findViewById(R.id.detail_cost);
                    detail_cost.setText("$" + String.format("%.2f", coffee_cost * cnt));
                }
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
}
