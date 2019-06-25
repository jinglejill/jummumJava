package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class SaveOrderTaking {

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("saveOrderTakingID")
    @Expose
    private Integer saveOrderTakingID;
    @SerializedName("takeAwayPrice")
    @Expose
    private Integer takeAwayPrice;
    @SerializedName("notePrice")
    @Expose
    private Integer notePrice;
    @SerializedName("customerTableID")
    @Expose
    private Integer customerTableID;
    @SerializedName("modifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("saveReceiptID")
    @Expose
    private Integer saveReceiptID;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("menuID")
    @Expose
    private Integer menuID;
    @SerializedName("branchID")
    @Expose
    private Integer branchID;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("specialPrice")
    @Expose
    private Integer specialPrice;
    @SerializedName("takeAway")
    @Expose
    private Integer takeAway;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSaveOrderTakingID() {
        return saveOrderTakingID;
    }

    public void setSaveOrderTakingID(Integer saveOrderTakingID) {
        this.saveOrderTakingID = saveOrderTakingID;
    }

    public Integer getTakeAwayPrice() {
        return takeAwayPrice;
    }

    public void setTakeAwayPrice(Integer takeAwayPrice) {
        this.takeAwayPrice = takeAwayPrice;
    }

    public Integer getNotePrice() {
        return notePrice;
    }

    public void setNotePrice(Integer notePrice) {
        this.notePrice = notePrice;
    }

    public Integer getCustomerTableID() {
        return customerTableID;
    }

    public void setCustomerTableID(Integer customerTableID) {
        this.customerTableID = customerTableID;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Integer getSaveReceiptID() {
        return saveReceiptID;
    }

    public void setSaveReceiptID(Integer saveReceiptID) {
        this.saveReceiptID = saveReceiptID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Integer specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Integer getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(Integer takeAway) {
        this.takeAway = takeAway;
    }

}