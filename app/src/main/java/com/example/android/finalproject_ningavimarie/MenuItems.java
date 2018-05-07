package com.example.android.finalproject_ningavimarie;

public class MenuItems {

    public int title;
    public int description;
    public int picture;
    public int price;


    public MenuItems(int title, int description, int picture, int price){
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.price = price;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
