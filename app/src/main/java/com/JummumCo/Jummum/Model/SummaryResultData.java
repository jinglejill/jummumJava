package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class SummaryResultData {

    @SerializedName("orderTaking")
    @Expose
    private List<OrderTakingResultData> orderTaking = null;
    @SerializedName("voucherCode")
    @Expose
    private String voucherCode;
    @SerializedName("branchID")
    @Expose
    private String branchID;

    @SerializedName("userAccountID")
    @Expose
    private String userAccountID;

    @SerializedName("orderNote")
    @Expose
    private List<CreateOrderNote> orderNote = null;


    public List<OrderTakingResultData> getOrderTaking() {
        return orderTaking;
    }

    public void setOrderTaking(List<OrderTakingResultData> orderTaking) {
        this.orderTaking = orderTaking;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(String userAccountID) {
        this.userAccountID = userAccountID;
    }

    public List<CreateOrderNote> getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(List<CreateOrderNote> orderNote) {
        this.orderNote = orderNote;
    }
}