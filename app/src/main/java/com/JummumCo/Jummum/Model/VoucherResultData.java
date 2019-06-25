package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class VoucherResultData {

    @SerializedName("PromotionID")
    private String PromotionID;

    @SerializedName("Header")
    private String Header;

    @SerializedName("SubTitle")
    private String SubTitle;

    @SerializedName("ImageUrl")
    private String ImageUrl;

    @SerializedName("TermsConditions")
    private String TermsConditions;

    @SerializedName("Type")
    private String Type;

    @SerializedName("OrderNo")
    private String OrderNo;

    public String getPromotionID() {
        return PromotionID;
    }

    public String getHeader() {
        return Header;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getTermsConditions() {
        return TermsConditions;
    }

    public String getType() {
        return Type;
    }

    public String getOrderNo() {
        return OrderNo;
    }
}
