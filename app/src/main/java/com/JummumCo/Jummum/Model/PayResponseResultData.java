package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PayResponseResultData {

    @SerializedName("ReceiptID")
    @Expose
    private String receiptID;
    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("CustomerTableID")
    @Expose
    private String customerTableID;
    @SerializedName("MemberID")
    @Expose
    private String memberID;
    @SerializedName("ServingPerson")
    @Expose
    private String servingPerson;
    @SerializedName("CustomerType")
    @Expose
    private String customerType;
    @SerializedName("OpenTableDate")
    @Expose
    private String openTableDate;
    @SerializedName("CashAmount")
    @Expose
    private String cashAmount;
    @SerializedName("CashReceive")
    @Expose
    private String cashReceive;
    @SerializedName("CreditCardType")
    @Expose
    private String creditCardType;
    @SerializedName("CreditCardNo")
    @Expose
    private String creditCardNo;
    @SerializedName("CreditCardAmount")
    @Expose
    private String creditCardAmount;
    @SerializedName("TransferDate")
    @Expose
    private String transferDate;
    @SerializedName("TransferAmount")
    @Expose
    private String transferAmount;
    @SerializedName("Remark")
    @Expose
    private String remark;
    @SerializedName("DiscountType")
    @Expose
    private String discountType;
    @SerializedName("DiscountAmount")
    @Expose
    private String discountAmount;
    @SerializedName("DiscountValue")
    @Expose
    private String discountValue;
    @SerializedName("DiscountReason")
    @Expose
    private String discountReason;
    @SerializedName("ServiceChargePercent")
    @Expose
    private String serviceChargePercent;
    @SerializedName("ServiceChargeValue")
    @Expose
    private String serviceChargeValue;
    @SerializedName("PriceIncludeVat")
    @Expose
    private String priceIncludeVat;
    @SerializedName("VatPercent")
    @Expose
    private String vatPercent;
    @SerializedName("VatValue")
    @Expose
    private String vatValue;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("StatusRoute")
    @Expose
    private String statusRoute;
    @SerializedName("ReceiptNoID")
    @Expose
    private String receiptNoID;
    @SerializedName("ReceiptNoTaxID")
    @Expose
    private String receiptNoTaxID;
    @SerializedName("ReceiptDate")
    @Expose
    private String receiptDate;
    @SerializedName("SendToKitchenDate")
    @Expose
    private String sendToKitchenDate;
    @SerializedName("DeliveredDate")
    @Expose
    private String deliveredDate;
    @SerializedName("MergeReceiptID")
    @Expose
    private String mergeReceiptID;
    @SerializedName("ModifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;

    @SerializedName("LuckyDrawCount")
    @Expose
    private String luckyDrawCount;

    @SerializedName("OrderTakingID")
    @Expose
    private String orderTakingID;
    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("SpecialPrice")
    @Expose
    private String specialPrice;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("TakeAway")
    @Expose
    private String takeAway;
    @SerializedName("NoteIDListInText")
    @Expose
    private String noteIDListInText;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("JummumLogo")
    @Expose
    private String jummumLogo;


    @SerializedName("ThankYouText")
    @Expose
    private String thankYouText;

    @SerializedName("ShowOrderBuffetButton")
    @Expose
    private boolean showOrderBuffetButton;

    @SerializedName("BuffetReceiptID")
    @Expose
    private String buffetReceiptID;

    @SerializedName("GBPrimeQRPostUrl")
    @Expose
    private String gBPrimeQRPostUrl;
    @SerializedName("GBPrimeQRToken")
    @Expose
    private String gBPrimeQRToken;
    @SerializedName("ResponseUrl")
    @Expose
    private String responseUrl;
    @SerializedName("BackgroundUrl")
    @Expose
    private String backgroundUrl;
    @SerializedName("ReferenceNo")
    @Expose
    private String referenceNo;
    @SerializedName("TotalAmount")
    @Expose
    private String totalAmount;

    @SerializedName("NetTotal")
    @Expose
    private String netTotal;

    @SerializedName("AfterDiscount")
    @Expose
    private String afterDiscount;


    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
    }

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

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getServingPerson() {
        return servingPerson;
    }

    public void setServingPerson(String servingPerson) {
        this.servingPerson = servingPerson;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getOpenTableDate() {
        return openTableDate;
    }

    public void setOpenTableDate(String openTableDate) {
        this.openTableDate = openTableDate;
    }

    public String getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(String cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getCashReceive() {
        return cashReceive;
    }

    public void setCashReceive(String cashReceive) {
        this.cashReceive = cashReceive;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCreditCardAmount() {
        return creditCardAmount;
    }

    public void setCreditCardAmount(String creditCardAmount) {
        this.creditCardAmount = creditCardAmount;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public String getServiceChargePercent() {
        return serviceChargePercent;
    }

    public void setServiceChargePercent(String serviceChargePercent) {
        this.serviceChargePercent = serviceChargePercent;
    }

    public String getServiceChargeValue() {
        return serviceChargeValue;
    }

    public void setServiceChargeValue(String serviceChargeValue) {
        this.serviceChargeValue = serviceChargeValue;
    }

    public String getPriceIncludeVat() {
        return priceIncludeVat;
    }

    public void setPriceIncludeVat(String priceIncludeVat) {
        this.priceIncludeVat = priceIncludeVat;
    }

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
    }

    public String getVatValue() {
        return vatValue;
    }

    public void setVatValue(String vatValue) {
        this.vatValue = vatValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusRoute() {
        return statusRoute;
    }

    public void setStatusRoute(String statusRoute) {
        this.statusRoute = statusRoute;
    }

    public String getReceiptNoID() {
        return receiptNoID;
    }

    public void setReceiptNoID(String receiptNoID) {
        this.receiptNoID = receiptNoID;
    }

    public String getReceiptNoTaxID() {
        return receiptNoTaxID;
    }

    public void setReceiptNoTaxID(String receiptNoTaxID) {
        this.receiptNoTaxID = receiptNoTaxID;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getSendToKitchenDate() {
        return sendToKitchenDate;
    }

    public void setSendToKitchenDate(String sendToKitchenDate) {
        this.sendToKitchenDate = sendToKitchenDate;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getMergeReceiptID() {
        return mergeReceiptID;
    }

    public void setMergeReceiptID(String mergeReceiptID) {
        this.mergeReceiptID = mergeReceiptID;
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

    public String getOrderTakingID() {
        return orderTakingID;
    }

    public void setOrderTakingID(String orderTakingID) {
        this.orderTakingID = orderTakingID;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(String specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(String takeAway) {
        this.takeAway = takeAway;
    }

    public String getNoteIDListInText() {
        return noteIDListInText;
    }

    public void setNoteIDListInText(String noteIDListInText) {
        this.noteIDListInText = noteIDListInText;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getJummumLogo() {
        return jummumLogo;
    }

    public void setJummumLogo(String jummumLogo) {
        this.jummumLogo = jummumLogo;
    }

    public String getLuckyDrawCount() {
        return luckyDrawCount;
    }

    public void setLuckyDrawCount(String luckyDrawCount) {
        this.luckyDrawCount = luckyDrawCount;
    }

    public String getThankYouText() {
        return thankYouText;
    }

    public void setThankYouText(String thankYouText) {
        this.thankYouText = thankYouText;
    }

    public boolean getShowOrderBuffetButton() {
        return showOrderBuffetButton;
    }

    public void setShowOrderBuffetButton(boolean showOrderBuffetButton) {
        this.showOrderBuffetButton = showOrderBuffetButton;
    }

    public String getBuffetReceiptID() {
        return buffetReceiptID;
    }

    public void setBuffetReceiptID(String buffetReceiptID) {
        this.buffetReceiptID = buffetReceiptID;
    }

    public boolean isShowOrderBuffetButton() {
        return showOrderBuffetButton;
    }

    public String getgBPrimeQRPostUrl() {
        return gBPrimeQRPostUrl;
    }

    public void setgBPrimeQRPostUrl(String gBPrimeQRPostUrl) {
        this.gBPrimeQRPostUrl = gBPrimeQRPostUrl;
    }

    public String getgBPrimeQRToken() {
        return gBPrimeQRToken;
    }

    public void setgBPrimeQRToken(String gBPrimeQRToken) {
        this.gBPrimeQRToken = gBPrimeQRToken;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(String netTotal) {
        this.netTotal = netTotal;
    }

    public String getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(String afterDiscount) {
        this.afterDiscount = afterDiscount;
    }
}