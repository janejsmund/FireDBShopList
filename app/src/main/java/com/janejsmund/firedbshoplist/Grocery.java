package com.janejsmund.firedbshoplist;

/**
 * Created by Janek on 03-Jan-18.
 */

public class Grocery {

    private String id;
    private String name;
    private String amount;
    private String price;
    private String amountBought;

    public Grocery(String id, String name, String amount, String price, String amountBought) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.amountBought = amountBought;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(String amountBought) {
        this.amountBought = amountBought;
    }
}
