package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class UserPromotionUsedResultData {

    @SerializedName("userAccountID")
    @Expose
    private int userAccountID;
    @SerializedName("userPromotionUsedID")
    @Expose
    private int userPromotionUsedID;
    @SerializedName("promotionID")
    @Expose
    private int promotionID;
    @SerializedName("receiptID")
    @Expose
    private int receiptID;
    @SerializedName("modifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;

    public int getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(int userAccountID) {
        this.userAccountID = userAccountID;
    }

    public int getUserPromotionUsedID() {
        return userPromotionUsedID;
    }

    public void setUserPromotionUsedID(int userPromotionUsedID) {
        this.userPromotionUsedID = userPromotionUsedID;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
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