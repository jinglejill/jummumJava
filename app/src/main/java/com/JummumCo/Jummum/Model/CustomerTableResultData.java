package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class CustomerTableResultData {
    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("CustomerTableID")
    @Expose
    private String customerTableID;
    @SerializedName("TableName")
    @Expose
    private String tableName;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Color")
    @Expose
    private String color;
    @SerializedName("Zone")
    @Expose
    private String zone;
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

    public String getCustomerTableID() {
        return customerTableID;
    }

    public void setCustomerTableID(String customerTableID) {
        this.customerTableID = customerTableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
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