package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class SaveOrderInsertData {

    @SerializedName("saveReceiptID")
    @Expose
    private Integer saveReceiptID;
    @SerializedName("memberID")
    @Expose
    private Integer memberID;
    @SerializedName("modifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("customerTableID")
    @Expose
    private Integer customerTableID;
    @SerializedName("voucherCode")
    @Expose
    private String voucherCode;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("branchID")
    @Expose
    private Integer branchID;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("buffetReceiptID")
    @Expose
    private Integer buffetReceiptID;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("saveOrderTaking")
    @Expose
    private List<SaveOrderTaking> saveOrderTaking = null;
    @SerializedName("saveOrderNote")
    @Expose
    private List<SaveOrderNote> saveOrderNote = null;

    public Integer getSaveReceiptID() {
        return saveReceiptID;
    }

    public void setSaveReceiptID(Integer saveReceiptID) {
        this.saveReceiptID = saveReceiptID;
    }

    public Integer getMemberID() {
        return memberID;
    }

    public void setMemberID(Integer memberID) {
        this.memberID = memberID;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Integer getCustomerTableID() {
        return customerTableID;
    }

    public void setCustomerTableID(Integer customerTableID) {
        this.customerTableID = customerTableID;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getBuffetReceiptID() {
        return buffetReceiptID;
    }

    public void setBuffetReceiptID(Integer buffetReceiptID) {
        this.buffetReceiptID = buffetReceiptID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SaveOrderTaking> getSaveOrderTaking() {
        return saveOrderTaking;
    }

    public void setSaveOrderTaking(List<SaveOrderTaking> saveOrderTaking) {
        this.saveOrderTaking = saveOrderTaking;
    }

    public List<SaveOrderNote> getSaveOrderNote() {
        return saveOrderNote;
    }

    public void setSaveOrderNote(List<SaveOrderNote> saveOrderNote) {
        this.saveOrderNote = saveOrderNote;
    }
}