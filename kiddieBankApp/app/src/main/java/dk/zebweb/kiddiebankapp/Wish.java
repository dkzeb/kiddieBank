package dk.zebweb.kiddiebankapp;

import android.graphics.drawable.Drawable;

/**
 * Created by zeb on 12-03-16.
 */
public class Wish {

    private int id;
    private int image;
    private String name;
    private int price;
    private int balance;
    private String starColor;

    public Wish(String name, int image, int price, int balance){
        this.name = name;
        this.image = image;
        this.price = price;
        this.balance = balance;
    }

    public Drawable getImage(){
        return Drawable.createFromPath("drawable://"+image);
    }

    public int getImageID(){
        return image;
    }

    public String getName(){ return this.name; }

    public void addMoney(int amt){
        balance += amt;
    }

    public int getPrice(){
        return price;
    }

    public int getBalance(){
        return balance;
    }

}
