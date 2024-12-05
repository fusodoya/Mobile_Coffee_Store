package com.example.coffeestore.Storage.Reward;

public class RewardItem {
    String name,dayTime;
    int point;

    public RewardItem(String name, String dayTime, int point) {
        this.name = name;
        this.dayTime = dayTime;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
