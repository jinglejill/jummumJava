package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class DisputeResultData {
    @SerializedName("DisputeReasonID")
    @Expose
    private String disputeReasonID;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("TextEn")
    @Expose
    private String textEn;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("ModifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("DisputeID")
    @Expose
    private String disputeID;
    @SerializedName("ReceiptID")
    @Expose
    private String receiptID;
    @SerializedName("StatusDetail")
    @Expose
    private String statusDetail;
    @SerializedName("DisputeReason")
    @Expose
    private String disputeReason;
    @SerializedName("RefundAmount")
    @Expose
    private String refundAmount;
    @SerializedName("Detail")
    @Expose
    private String detail;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;


    public String getDisputeID() {
        return disputeID;
    }

    public void setDisputeID(String disputeID) {
        this.disputeID = disputeID;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public String getDisputeReason() {
        return disputeReason;
    }

    public void setDisputeReason(String disputeReason) {
        this.disputeReason = disputeReason;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDisputeReasonID() {
        return disputeReasonID;
    }

    public void setDisputeReasonID(String disputeReasonID) {
        this.disputeReasonID = disputeReasonID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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