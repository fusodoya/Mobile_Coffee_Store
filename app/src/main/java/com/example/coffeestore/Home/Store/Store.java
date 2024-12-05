package com.example.coffeestore.Home.Store;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coffeestore.Home.CommonFragment.LoyaltyCard;
import com.example.coffeestore.MyCart.MyCart;
import com.example.coffeestore.Profile.Profile;
import com.example.coffeestore.R;
import com.example.coffeestore.Storage.CoffeeMenu.CoffeeMenu;
<<<<<<< HEAD
import com.example.coffeestore.Storage.CoffeeMenu.ItemAdapter;
import com.example.coffeestore.Storage.User.UserInfo;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
=======
import com.example.coffeestore.Storage.CoffeeMenu.CoffeeAdapter;
import com.example.coffeestore.Storage.User.UserInfo;

import org.jetbrains.annotations.Nullable;
>>>>>>> f3a1f3e (build cleanning)

public class Store extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout của fragment
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView user_name = view.findViewById(R.id.store_user_name);
        user_name.setText(UserInfo.getName());

        ImageView cart = view.findViewById(R.id.cart);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MyCart.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("is_return", true);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        ImageView user = view.findViewById(R.id.user);
        user.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), Profile.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
            ((Activity) requireContext()).finish();
        });

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.loyalty_card_fragment_container, new LoyaltyCard(requireContext(), "Store"))
                .commit();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
<<<<<<< HEAD
        ItemAdapter adapter = new ItemAdapter(requireContext(), CoffeeMenu.list);
=======
        CoffeeAdapter adapter = new CoffeeAdapter(requireContext(), CoffeeMenu.list);
>>>>>>> f3a1f3e (build cleanning)
        recyclerView.setAdapter(adapter);

        // Đổi màu nền menu và thanh nav
        LinearLayout roundedLayout = view.findViewById(R.id.menu_and_nav);
        GradientDrawable menu_and_nav_drawable = (GradientDrawable) roundedLayout.getBackground();
        menu_and_nav_drawable.setColor(ContextCompat.getColor(requireContext(), R.color.background_dark));
    }
}