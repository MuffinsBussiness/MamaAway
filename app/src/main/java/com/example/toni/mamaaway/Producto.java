package com.example.toni.mamaaway;

/**
 * Created by toni on 14/10/17.
 */

public class Producto {

    private String name;
    private int quantity;
    private String owner;
    private double price;
    private boolean paid;


    public Producto() {
    }

    public Producto(String name, int quantity, String owner, double price) {
        this.name = name;
        this.quantity = quantity;
        this.owner = owner;
        this.price = price;
        this.paid = false;
    }

    public Producto(String name, int quantity, String owner, double price, boolean paid) {
        this.name = name;
        this.quantity = quantity;
        this.owner = owner;
        this.price = price;
        this.paid = paid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
