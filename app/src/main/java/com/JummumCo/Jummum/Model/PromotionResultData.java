package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PromotionResultData {

    @SerializedName("PromotionID")
    @Expose
    private String promotionID;
    @SerializedName("MainBranchID")
    @Expose
    private String mainBranchID;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("UsingStartDate")
    @Expose
    private String usingStartDate;
    @SerializedName("UsingEndDate")
    @Expose
    private String usingEndDate;
    @SerializedName("Header")
    @Expose
    private String header;
    @SerializedName("SubTitle")
    @Expose
    private String subTitle;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("DiscountType")
    @Expose
    private String discountType;
    @SerializedName("DiscountAmount")
    @Expose
    private String discountAmount;
    @SerializedName("MinimumSpending")
    @Expose
    private String minimumSpending;
    @SerializedName("MaxDiscountAmountPerDay")
    @Expose
    private String maxDiscountAmountPerDay;
    @SerializedName("AllowEveryone")
    @Expose
    private String allowEveryone;
    @SerializedName("AllowDiscountForAllMenuType")
    @Expose
    private String allowDiscountForAllMenuType;
    @SerializedName("DiscountMenuID")
    @Expose
    private String discountMenuID;
    @SerializedName("NoOfLimitUse")
    @Expose
    private String noOfLimitUse;
    @SerializedName("NoOfLimitUsePerUser")
    @Expose
    private String noOfLimitUsePerUser;
    @SerializedName("NoOfLimitUsePerUserPerDay")
    @Expose
    private String noOfLimitUsePerUserPerDay;
    @SerializedName("VoucherCode")
    @Expose
    private String voucherCode;
    @SerializedName("TermsConditions")
    @Expose
    private String termsConditions;
    @SerializedName("Type")
    @Expose
    private String type;
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
    @SerializedName("MoreDiscountToGo")
    @Expose
    private String moreDiscountToGo;
    @SerializedName("PromoCodeID")
    @Expose
    private String promoCodeID;

    @SerializedName("Text")
    @Expose
    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public String getMainBranchID() {
        return mainBranchID;
    }

    public void setMainBranchID(String mainBranchID) {
        this.mainBranchID = mainBranchID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUsingStartDate() {
        return usingStartDate;
    }

    public void setUsingStartDate(String usingStartDate) {
        this.usingStartDate = usingStartDate;
    }

    public String getUsingEndDate() {
        return usingEndDate;
    }

    public void setUsingEndDate(String usingEndDate) {
        this.usingEndDate = usingEndDate;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getMinimumSpending() {
        return minimumSpending;
    }

    public void setMinimumSpending(String minimumSpending) {
        this.minimumSpending = minimumSpending;
    }

    public String getMaxDiscountAmountPerDay() {
        return maxDiscountAmountPerDay;
    }

    public void setMaxDiscountAmountPerDay(String maxDiscountAmountPerDay) {
        this.maxDiscountAmountPerDay = maxDiscountAmountPerDay;
    }

    public String getAllowEveryone() {
        return allowEveryone;
    }

    public void setAllowEveryone(String allowEveryone) {
        this.allowEveryone = allowEveryone;
    }

    public String getAllowDiscountForAllMenuType() {
        return allowDiscountForAllMenuType;
    }

    public void setAllowDiscountForAllMenuType(String allowDiscountForAllMenuType) {
        this.allowDiscountForAllMenuType = allowDiscountForAllMenuType;
    }

    public String getDiscountMenuID() {
        return discountMenuID;
    }

    public void setDiscountMenuID(String discountMenuID) {
        this.discountMenuID = discountMenuID;
    }

    public String getNoOfLimitUse() {
        return noOfLimitUse;
    }

    public void setNoOfLimitUse(String noOfLimitUse) {
        this.noOfLimitUse = noOfLimitUse;
    }

    public String getNoOfLimitUsePerUser() {
        return noOfLimitUsePerUser;
    }

    public void setNoOfLimitUsePerUser(String noOfLimitUsePerUser) {
        this.noOfLimitUsePerUser = noOfLimitUsePerUser;
    }

    public String getNoOfLimitUsePerUserPerDay() {
        return noOfLimitUsePerUserPerDay;
    }

    public void setNoOfLimitUsePerUserPerDay(String noOfLimitUsePerUserPerDay) {
        this.noOfLimitUsePerUserPerDay = noOfLimitUsePerUserPerDay;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getTermsConditions() {
        return termsConditions;
    }

    public void setTermsConditions(String termsConditions) {
        this.termsConditions = termsConditions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMoreDiscountToGo() {
        return moreDiscountToGo;
    }

    public void setMoreDiscountToGo(String moreDiscountToGo) {
        this.moreDiscountToGo = moreDiscountToGo;
    }

    public String getPromoCodeID() {
        return promoCodeID;
    }

    public void setPromoCodeID(String promoCodeID) {
        this.promoCodeID = promoCodeID;
    }
}