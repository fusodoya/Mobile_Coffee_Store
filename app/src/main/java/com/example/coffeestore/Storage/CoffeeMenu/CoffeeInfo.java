package com.example.coffeestore.Storage.CoffeeMenu;

public class CoffeeInfo {
    private int imgID, ptsRedeem;
    private String name;
    private double price;

    public CoffeeInfo(int imgID, String name, double price, int ptsRedeem) {
        this.imgID = imgID;
        this.name = name;
        this.price = price;
        this.ptsRedeem = ptsRedeem;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgId) {
        this.imgID = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPtsRedeem() {
        return ptsRedeem;
    }

    public void setPtsRedeem(int ptsRedeem) {
        this.ptsRedeem = ptsRedeem;
    }
}
