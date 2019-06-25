package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class RatingResultData {

    @SerializedName("Rating")
    @Expose
    private List<Rating> rating;

    @SerializedName("Dispute")
    @Expose
    private List<DisputeResultData> dispute;

    public List<DisputeResultData> getDispute() {
        return dispute;
    }

    public void setDispute(List<DisputeResultData> dispute) {
        this.dispute = dispute;
    }

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

}