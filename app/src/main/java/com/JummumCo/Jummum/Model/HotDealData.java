package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class HotDealData {

    @SerializedName("rank")
    private String rank;

    @SerializedName("Frequency")
    private String Frequency;

    @SerializedName("Sales")
    private String Sales;

    @SerializedName("PromotionID")
    private String PromotionID;

    @SerializedName("MainBranchID")
    private String MainBranchID;

    @SerializedName("Type")
    private String Type;

    @SerializedName("Header")
    private String Header;

    @SerializedName("SubTitle")
    private String SubTitle;

    @SerializedName("TermsConditions")
    private String TermsConditions;

    @SerializedName("ImageUrl")
    private String ImageUrl;

    @SerializedName("OrderNo")
    private String OrderNo;

    @SerializedName("DiscountGroupMenuID")
    private String DiscountGroupMenuID;

    @SerializedName("VoucherCode")
    private String VoucherCode;

    @SerializedName("HotDeal0Reward1")
    private String HotDeal0Reward1;


    @SerializedName("RewardRedemptionID")
    @Expose
    private String rewardRedemptionID;
    @SerializedName("Point")
    @Expose
    private String point;
    @SerializedName("WithInPeriod")
    @Expose
    private String withInPeriod;
    @SerializedName("RewardRank")
    @Expose
    private String rewardRank;
    @SerializedName(value = "RedeemDate",alternate = {"redeemDate"})
    @Expose
    private String redeemDate;
    @SerializedName("BranchImageUrl")
    @Expose
    private String branchImageUrl;
    @SerializedName(value = "ShowOrderNow",alternate = {"showOrderNow"})
    @Expose
    private String showOrderNow;

    @SerializedName(value = "TimeToCountDown",alternate = {"timeToCountDown"})
    @Expose
    private String timeToCountDown;

    @SerializedName(value = "ExpiredDate",alternate = {"expiredDate"})
    @Expose
    private String expiredDate;


    @SerializedName(value = "Code",alternate = {"code"})
    @Expose
    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRank() {
        return rank;
    }

    public String getFrequency() {
        return Frequency;
    }

    public String getSales() {
        return Sales;
    }

    public String getPromotionID() {
        return PromotionID;
    }

    public String getMainBranchID() {
        return MainBranchID;
    }

    public String getType() {
        return Type;
    }

    public String getHeader() {
        return Header;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public String getTermsConditions() {
        return TermsConditions;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public String getDiscountGroupMenuID() {
        return DiscountGroupMenuID;
    }

    public String getVoucherCode() {
        return VoucherCode;
    }
    public String getHotDeal0Reward1() {
        return HotDeal0Reward1;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public void setVoucherCode(String voucherCode) {
        VoucherCode = voucherCode;
    }
    public void setHotDeal0Reward1(String hotDeal0Reward1) {
        HotDeal0Reward1 = hotDeal0Reward1;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    public void setSales(String sales) {
        Sales = sales;
    }

    public void setPromotionID(String promotionID) {
        PromotionID = promotionID;
    }

    public void setMainBranchID(String mainBranchID) {
        MainBranchID = mainBranchID;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getWithInPeriod() {
        return withInPeriod;
    }

    public void setWithInPeriod(String withInPeriod) {
        this.withInPeriod = withInPeriod;
    }

    public String getRewardRank() {
        return rewardRank;
    }

    public void setRewardRank(String rewardRank) {
        this.rewardRank = rewardRank;
    }

    public String getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(String redeemDate) {
        this.redeemDate = redeemDate;
    }

    public String getBranchImageUrl() {
        return branchImageUrl;
    }

    public void setBranchImageUrl(String branchImageUrl) {
        this.branchImageUrl = branchImageUrl;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    public void setTermsConditions(String termsConditions) {
        TermsConditions = termsConditions;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public void setDiscountGroupMenuID(String discountGroupMenuID) {
        DiscountGroupMenuID = discountGroupMenuID;
    }

    public String getRewardRedemptionID() {
        return rewardRedemptionID;
    }

    public void setRewardRedemptionID(String rewardRedemptionID) {
        this.rewardRedemptionID = rewardRedemptionID;
    }

    public String getShowOrderNow() {
        return showOrderNow;
    }

    public void setShowOrderNow(String showOrderNow) {
        this.showOrderNow = showOrderNow;
    }

    public String getTimeToCountDown() {
        return timeToCountDown;
    }

    public void setTimeToCountDown(String timeToCountDown) {
        this.timeToCountDown = timeToCountDown;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
