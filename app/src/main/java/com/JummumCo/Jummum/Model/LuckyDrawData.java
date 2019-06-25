package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class LuckyDrawData {

    @SerializedName("Header")
    private String header;

    @SerializedName("RewardRank")
    private String rewardRank;

    @SerializedName("LuckyDrawCount")
    private String luckyDrawCount;

    @SerializedName("DiscountGroupMenuID")
    private String discountGroupMenuID;

    @SerializedName("VoucherCode")
    private String voucherCode;

    @SerializedName(value = "ShowOrderNow",alternate = {"showOrderNow"})
    private String showOrderNow;

    public String getHeader() {
        return header;
    }

    public String getRewardRank() {
        return rewardRank;
    }

    public String getLuckyDrawCount() {
        return luckyDrawCount;
    }

    public String getDiscountGroupMenuID() {
        return discountGroupMenuID;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setRewardRank(String rewardRank) {
        this.rewardRank = rewardRank;
    }

    public void setLuckyDrawCount(String luckyDrawCount) {
        this.luckyDrawCount = luckyDrawCount;
    }

    public void setDiscountGroupMenuID(String discountGroupMenuID) {
        this.discountGroupMenuID = discountGroupMenuID;
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
