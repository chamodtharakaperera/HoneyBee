package com.metroapps.honeybee.model;

public class FoodItem {
    String name, price;
    String restaurantName;
    int imageUrl;

    public FoodItem(String name, String price, String restaurantName, int imageUrl) {
        this.name = name;
        this.price = price;
        this.restaurantName = restaurantName;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
