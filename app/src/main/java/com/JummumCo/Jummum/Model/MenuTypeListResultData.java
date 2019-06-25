package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class MenuTypeListResultData {

    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("MenuTypeID")
    @Expose
    private String menuTypeID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("AllowDiscount")
    @Expose
    private String allowDiscount;
    @SerializedName("Color")
    @Expose
    private String color;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("ModifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getMenuTypeID() {
        return menuTypeID;
    }

    public void setMenuTypeID(String menuTypeID) {
        this.menuTypeID = menuTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllowDiscount() {
        return allowDiscount;
    }

    public void setAllowDiscount(String allowDiscount) {
        this.allowDiscount = allowDiscount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }




}