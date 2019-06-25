package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatingResponse extends BaseResponse{

    @SerializedName("data")
    private List<RatingResultData> ratingResultData;

    public List<RatingResultData> getRatingResultData() {
        return ratingResultData;
    }
}