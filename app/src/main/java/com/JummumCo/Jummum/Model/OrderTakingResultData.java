package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class OrderTakingResultData {

    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("noteIDListInText")
    @Expose
    private String noteIDListInText;
    @SerializedName("modifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("customerTableID")
    @Expose
    private int customerTableID;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("menuID")
    @Expose
    private int menuID;
    @SerializedName("branchID")
    @Expose
    private int branchID;
    @SerializedName("orderNo")
    @Expose
    private int orderNo;
    @SerializedName("orderTakingID")
    @Expose
    private int orderTakingID;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("specialPrice")
    @Expose
    private int specialPrice;
    @SerializedName("receiptID")
    @Expose
    private int receiptID;
    @SerializedName("takeAway")
    @Expose
    private int takeAway;
    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("takeAwayPrice")
    @Expose
    private int takeAwayPrice;
    @SerializedName("notePrice")
    @Expose
    private String notePrice;


    public int getTakeAwayPrice() {
        return takeAwayPrice;
    }


    public String getNotePrice() {
        return notePrice;
    }

    public void setNotePrice(String notePrice) {
        this.notePrice = notePrice;
    }

    public void setTakeAwayPrice(int takeAwayPrice) {
        this.takeAwayPrice = takeAwayPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNoteIDListInText() {
        return noteIDListInText;
    }

    public void setNoteIDListInText(String noteIDListInText) {
        this.noteIDListInText = noteIDListInText;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public int getCustomerTableID() {
        return customerTableID;
    }

    public void setCustomerTableID(int customerTableID) {
        this.customerTableID = customerTableID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderTakingID() {
        return orderTakingID;
    }

    public void setOrderTakingID(int orderTakingID) {
        this.orderTakingID = orderTakingID;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public int getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(int takeAway) {
        this.takeAway = takeAway;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}