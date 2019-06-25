package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayResultDataPayment {
    @SerializedName("customerType")
    @Expose
    private int customerType;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("statusRoute")
    @Expose
    private String statusRoute;
    @SerializedName("receiptID")
    @Expose
    private int receiptID;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("userPromotionUsed")
    @Expose
    private UserPromotionUsedResultData userPromotionUsed;
    @SerializedName("modifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("orderTaking")
    @Expose
    private List<OrderTakingResultData> orderTaking = null;
    @SerializedName("orderNote")
    @Expose
    private List<CreateOrderNote> orderNote = null;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("sendToKitchenDate")
    @Expose
    private String sendToKitchenDate;
    @SerializedName("receiptDate")
    @Expose
    private String receiptDate;
    @SerializedName("discountType")
    @Expose
    private int discountType;
    @SerializedName("creditCardType")
    @Expose
    private int creditCardType;
    @SerializedName("customerTableID")
    @Expose
    private int customerTableID;
    @SerializedName("creditCardNo")
    @Expose
    private String creditCardNo;
    @SerializedName("creditCardAmount")
    @Expose
    private Double creditCardAmount;
    @SerializedName("mergeReceiptID")
    @Expose
    private int mergeReceiptID;
    @SerializedName("serviceChargePercent")
    @Expose
    private int serviceChargePercent;
    @SerializedName("serviceChargeValue")
    @Expose
    private Double serviceChargeValue;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;
    //    @SerializedName("orderNote")
//    @Expose
//    private List<Object> orderNote = null;
    @SerializedName("receiptNoID")
    @Expose
    private String receiptNoID;
    @SerializedName("cashAmount")
    @Expose
    private int cashAmount;
    @SerializedName("priceIncludeVat")
    @Expose
    private int priceIncludeVat;
    @SerializedName("servingPerson")
    @Expose
    private int servingPerson;
    @SerializedName("openTableDate")
    @Expose
    private String openTableDate;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("deliveredDate")
    @Expose
    private String deliveredDate;
    @SerializedName("discountReason")
    @Expose
    private String discountReason;
    @SerializedName("receiptNoTaxID")
    @Expose
    private String receiptNoTaxID;
    @SerializedName("discountAmount")
    @Expose
    private int discountAmount;
    @SerializedName("vatValue")
    @Expose
    private Double vatValue;
    @SerializedName("branchID")
    @Expose
    private int branchID;
    @SerializedName("memberID")
    @Expose
    private int memberID;
    @SerializedName("promoCodeID")
    @Expose
    private int promoCodeID;
    @SerializedName("cashReceive")
    @Expose
    private int cashReceive;
    @SerializedName("discountValue")
    @Expose
    private double discountValue;
    @SerializedName("vatPercent")
    @Expose
    private int vatPercent;
    @SerializedName("transferDate")
    @Expose
    private String transferDate;
    @SerializedName("omiseToken")
    @Expose
    private String omiseToken;
    @SerializedName("transferAmount")
    @Expose
    private int transferAmount;
    @SerializedName("buffetReceiptID")
    @Expose
    private int buffetReceiptID;
    @SerializedName("voucherCode")
    @Expose
    private String voucherCode;

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public int getBuffetReceiptID() {
        return buffetReceiptID;
    }

    public void setBuffetReceiptID(int buffetReceiptID) {
        this.buffetReceiptID = buffetReceiptID;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatusRoute() {
        return statusRoute;
    }

    public void setStatusRoute(String statusRoute) {
        this.statusRoute = statusRoute;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public UserPromotionUsedResultData getUserPromotionUsed() {
        return userPromotionUsed;
    }

    public void setUserPromotionUsed(UserPromotionUsedResultData userPromotionUsed) {
        this.userPromotionUsed = userPromotionUsed;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public List<OrderTakingResultData> getOrderTaking() {
        return orderTaking;
    }

    public void setOrderTaking(List<OrderTakingResultData> orderTaking) {
        this.orderTaking = orderTaking;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSendToKitchenDate() {
        return sendToKitchenDate;
    }

    public void setSendToKitchenDate(String sendToKitchenDate) {
        this.sendToKitchenDate = sendToKitchenDate;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public int getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(int creditCardType) {
        this.creditCardType = creditCardType;
    }

    public int getCustomerTableID() {
        return customerTableID;
    }

    public void setCustomerTableID(int customerTableID) {
        this.customerTableID = customerTableID;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public Double getCreditCardAmount() {
        return creditCardAmount;
    }

    public void setCreditCardAmount(Double creditCardAmount) {
        this.creditCardAmount = creditCardAmount;
    }

    public int getMergeReceiptID() {
        return mergeReceiptID;
    }

    public void setMergeReceiptID(int mergeReceiptID) {
        this.mergeReceiptID = mergeReceiptID;
    }

    public int getServiceChargePercent() {
        return serviceChargePercent;
    }

    public void setServiceChargePercent(int serviceChargePercent) {
        this.serviceChargePercent = serviceChargePercent;
    }

    public Double getServiceChargeValue() {
        return serviceChargeValue;
    }

    public void setServiceChargeValue(Double serviceChargeValue) {
        this.serviceChargeValue = serviceChargeValue;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /*
    public List<Object> getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(List<Object> orderNote) {
        this.orderNote = orderNote;
    }
    */
    public String getReceiptNoID() {
        return receiptNoID;
    }

    public void setReceiptNoID(String receiptNoID) {
        this.receiptNoID = receiptNoID;
    }

    public int getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(int cashAmount) {
        this.cashAmount = cashAmount;
    }

    public int getPriceIncludeVat() {
        return priceIncludeVat;
    }

    public void setPriceIncludeVat(int priceIncludeVat) {
        this.priceIncludeVat = priceIncludeVat;
    }

    public int getServingPerson() {
        return servingPerson;
    }

    public void setServingPerson(int servingPerson) {
        this.servingPerson = servingPerson;
    }

    public String getOpenTableDate() {
        return openTableDate;
    }

    public void setOpenTableDate(String openTableDate) {
        this.openTableDate = openTableDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public String getReceiptNoTaxID() {
        return receiptNoTaxID;
    }

    public void setReceiptNoTaxID(String receiptNoTaxID) {
        this.receiptNoTaxID = receiptNoTaxID;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getVatValue() {
        return vatValue;
    }

    public void setVatValue(Double vatValue) {
        this.vatValue = vatValue;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getPromoCodeID() {
        return promoCodeID;
    }

    public void setPromoCodeID(int promoCodeID) {
        this.promoCodeID = promoCodeID;
    }

    public int getCashReceive() {
        return cashReceive;
    }

    public void setCashReceive(int cashReceive) {
        this.cashReceive = cashReceive;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public int getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(int vatPercent) {
        this.vatPercent = vatPercent;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getOmiseToken() {
        return omiseToken;
    }

    public void setOmiseToken(String omiseToken) {
        this.omiseToken = omiseToken;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public List<CreateOrderNote> getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(List<CreateOrderNote> orderNote) {
        this.orderNote = orderNote;
    }
}
