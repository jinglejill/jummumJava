package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LuckyDrawResponse extends BaseResponse {

    @SerializedName("data")
    private List<List<LuckyDrawData>> luckyDrawData;

    public List<List<LuckyDrawData>> getData() {
        return luckyDrawData;
    }
}