package com.example.coffeestore.Storage.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserInfo {
    private static String name = "Anderson",
            phone_number = "+60134589525",
            email = "Anderson@email.com",
            address = "3 Addersion Court Chino Hills, HO56824, United State";

    public static String getName() {return name; }

    public static void setName(String name) {
        UserInfo.name = name;
    }

    public static String getPhone_number() {
        return phone_number;
    }

    public static void setPhone_number(String phone_number) { UserInfo.phone_number = phone_number; }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserInfo.email = email;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        UserInfo.address = address;
    }

    public static String getTimeDay() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM | hh:mm a", Locale.getDefault());
        return formatter.format(calendar.getTime());
    }
}
