package dk.zebweb.kiddiebankapp;

import android.graphics.drawable.Drawable;

/**
 * Created by zeb on 12-03-16.
 */
public class Wish {

    private String id;
    private String image;
    private String name;
    private int price;
    private int balance;
    private String starColor;

    public Wish(){

    }

    public Wish(String id, String name, String image, int price, int balance, String starColor){
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.balance = balance;
        this.starColor = starColor;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getBalance() {
        return balance;
    }

    public String getStarColor() {
        return starColor;
    }
}
