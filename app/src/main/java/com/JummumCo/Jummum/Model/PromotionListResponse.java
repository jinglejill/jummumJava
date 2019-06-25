package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromotionListResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<PromotionResultData>> promotionlistData;

    public List<List<PromotionResultData>> getPromotionlistData() {
        return promotionlistData;
    }
}