package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class OrderJoinResultData {

    @SerializedName("OrderJoiningID")
    @Expose
    private String orderJoiningID;
    @SerializedName("ReceiptID")
    @Expose
    private String receiptID;
    @SerializedName("MemberID")
    @Expose
    private String memberID;
    @SerializedName("ModifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;

    public String getOrderJoiningID() {
        return orderJoiningID;
    }

    public void setOrderJoiningID(String orderJoiningID) {
        this.orderJoiningID = orderJoiningID;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
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