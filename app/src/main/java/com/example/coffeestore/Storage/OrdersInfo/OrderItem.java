package com.example.coffeestore.Storage.OrdersInfo;

public class OrderItem {
    String time, coffeeName, address, price;

    public OrderItem(String time, String coffeeName, String address, String price) {
        this.time = time;
        this.coffeeName = coffeeName;
        this.address = address;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }
}
