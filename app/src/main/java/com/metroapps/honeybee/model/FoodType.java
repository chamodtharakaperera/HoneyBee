package com.metroapps.honeybee.model;

public class FoodType {
    String name;
    String imageUrl;


    public FoodType(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public FoodType() {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
