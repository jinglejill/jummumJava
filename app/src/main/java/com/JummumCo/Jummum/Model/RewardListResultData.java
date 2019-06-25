package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class RewardListResultData {
    @SerializedName("Num")
    @Expose
    private String num;
    @SerializedName("Frequency")
    @Expose
    private String frequency;
    @SerializedName("Sales")
    @Expose
    private String sales;
    @SerializedName("RewardRedemptionID")
    @Expose
    private String rewardRedemptionID;
    @SerializedName("MainBranchID")
    @Expose
    private String mainBranchID;
    @SerializedName("Header")
    @Expose
    private String header;
    @SerializedName("SubTitle")
    @Expose
    private String subTitle;
    @SerializedName("TermsConditions")
    @Expose
    private String termsConditions;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("Point")
    @Expose
    private String point;
    @SerializedName("UsingEndDate")
    @Expose
    private String usingEndDate;
    @SerializedName("WithinPeriod")
    @Expose
    private String withInPeriod;
    @SerializedName("WithInPeriod")
    @Expose
    private String withInPeriod2;
    @SerializedName("DiscountGroupMenuID")
    @Expose
    private String discountGroupMenuID;
    @SerializedName("BranchImageUrl")
    @Expose
    private String branchImageUrl;
    @SerializedName("PromoCodeID")
    @Expose
    private String promoCodeId;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("RemainingPoint")
    @Expose
    private String remainingPoint;
    @SerializedName("MemberID")
    @Expose
    private String memberID;
    @SerializedName("UsedDate")
    @Expose
    private String usedDate;
    @SerializedName("RedeemDate")
    @Expose
    private String redeemDate;
    @SerializedName("ExpiredDate")
    @Expose
    private String expiredDate;
    @SerializedName("TimeToCountDown")
    @Expose
    private String timeToCountDown;
    @SerializedName("Type")
    private String Type;
    @SerializedName("RewardRank")
    private String rewardRank;
    @SerializedName("VoucherCode")
    private String voucherCode;
    @SerializedName(value = "ShowOrderNow",alternate = {"showOrderNow"})
    private String showOrderNow;

    public String getTimeToCountDown() {
        return timeToCountDown;
    }

    public void setTimeToCountDown(String timeToCountDown) {
        this.timeToCountDown = timeToCountDown;
    }

    public String getWithInPeriod2() {
        return withInPeriod2;
    }

    public void setWithInPeriod2(String withInPeriod2) {
        this.withInPeriod2 = withInPeriod2;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(String usedDate) {
        this.usedDate = usedDate;
    }

    public String getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(String redeemDate) {
        this.redeemDate = redeemDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getRemainingPoint() {
        return remainingPoint;
    }

    public void setRemainingPoint(String remainingPoint) {
        this.remainingPoint = remainingPoint;
    }

    public String getPromoCodeId() {
        return promoCodeId;
    }

    public void setPromoCodeId(String promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getRewardRedemptionID() {
        return rewardRedemptionID;
    }

    public void setRewardRedemptionID(String rewardRedemptionID) {
        this.rewardRedemptionID = rewardRedemptionID;
    }

    public String getMainBranchID() {
        return mainBranchID;
    }

    public void setMainBranchID(String mainBranchID) {
        this.mainBranchID = mainBranchID;
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

    public String getTermsConditions() {
        return termsConditions;
    }

    public void setTermsConditions(String termsConditions) {
        this.termsConditions = termsConditions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getUsingEndDate() {
        return usingEndDate;
    }

    public void setUsingEndDate(String usingEndDate) {
        this.usingEndDate = usingEndDate;
    }

    public String getWithInPeriod() {
        return withInPeriod;
    }

    public void setWithInPeriod(String withInPeriod) {
        this.withInPeriod = withInPeriod;
    }

    public String getDiscountGroupMenuID() {
        return discountGroupMenuID;
    }

    public void setDiscountGroupMenuID(String discountGroupMenuID) {
        this.discountGroupMenuID = discountGroupMenuID;
    }

    public String getBranchImageUrl() {
        return branchImageUrl;
    }

    public void setBranchImageUrl(String branchImageUrl) {
        this.branchImageUrl = branchImageUrl;
    }


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRewardRank() {
        return rewardRank;
    }

    public void setRewardRank(String rewardRank) {
        this.rewardRank = rewardRank;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getShowOrderNow() {
        return showOrderNow;
    }

    public void setShowOrderNow(String showOrderNow) {
        this.showOrderNow = showOrderNow;
    }
}