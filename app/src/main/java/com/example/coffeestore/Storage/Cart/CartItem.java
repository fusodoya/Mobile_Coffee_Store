package com.example.coffeestore.Storage.Cart;

public class CartItem {
    private String name;
    private String details;
    private String price;
    private int quantity;
    private int imageResource;
    private double cost;

    public CartItem(String name, String details, String price, int quantity, int imageResource, double cost) {
        this.name = name;
        this.details = details;
        this.price = price;
        this.quantity = quantity;
        this.imageResource = imageResource;
        this.cost = cost;
    }

    public String getName() { return name; }
    public String getDetails() { return details; }
    public String getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getImageResource() { return imageResource; }
    public double getCost() {return cost; }
}
