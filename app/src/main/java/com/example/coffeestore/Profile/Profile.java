package com.example.coffeestore.Profile;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coffeestore.Home.Home;
import com.example.coffeestore.R;
import com.example.coffeestore.RedeemRewards.RedeemRewards;
import com.example.coffeestore.Storage.User.UserInfo;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView profileName, profilePhone, profileEmail, profileAddress;
        profileName = findViewById(R.id.profile_name);
        profilePhone = findViewById(R.id.profile_phone_number);
        profileEmail = findViewById(R.id.profile_email);
        profileAddress = findViewById(R.id.profile_address);

        profileName.setText(UserInfo.getName());
        profilePhone.setText(UserInfo.getPhone_number());
        profileEmail.setText(UserInfo.getEmail());
        profileAddress.setText(UserInfo.getAddress());

        ImageView my_cart_back = findViewById(R.id.profile_back_button);
        my_cart_back.setOnClickListener(view -> {
            UserInfo.setName((String) profileName.getText());
            UserInfo.setPhone_number((String) profilePhone.getText());
            UserInfo.setEmail((String) profileEmail.getText());
            UserInfo.setAddress((String) profileAddress.getText());

            Intent intent = new Intent(Profile.this, Home.class);
            Bundle bundle = new Bundle();
            bundle.putString("button", "Store");
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });


        ImageView editName, editPhone, editEmail, editAddress;

        // Ánh xạ các view
        editName = findViewById(R.id.edit_name);
        editPhone = findViewById(R.id.edit_phone);
        editEmail = findViewById(R.id.edit_email);
        editAddress = findViewById(R.id.edit_address);

        // Xử lý sự kiện khi nhấn vào icon edit
        editName.setOnClickListener(view -> showEditDialog("Full Name", profileName));
        editPhone.setOnClickListener(view -> showEditDialog("Phone Number", profilePhone, InputType.TYPE_CLASS_PHONE));
        editEmail.setOnClickListener(view -> showEditDialog("Email", profileEmail, InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS));
        editAddress.setOnClickListener(view -> showEditDialog("Address", profileAddress));
    }

    // Hàm hiển thị hộp thoại chỉnh sửa thông tin
    private void showEditDialog(String title, TextView textView) {
        showEditDialog(title, textView, InputType.TYPE_CLASS_TEXT);
    }

    private void showEditDialog(String title, TextView textView, int inputType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit " + title);

        final EditText input = new EditText(this);
        input.setInputType(inputType);
        input.setText(textView.getText().toString());
        builder.setView(input);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newValue = input.getText().toString().trim();
            if (!newValue.isEmpty()) {
                textView.setText(newValue);
                // Toast.makeText(Profile.this, title + " Updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Toast.makeText(Profile.this, title + " Cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}