package com.JummumCo.Jummum.Model;

import org.parceler.Parcel;

@Parcel
public class OrderSummary {
    private int qty;
    private String productName;
    private String noteName;
    private String price;
    private boolean take;


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public boolean isTake() {
        return take;
    }

    public void setTake(boolean take) {
        this.take = take;
    }
}
