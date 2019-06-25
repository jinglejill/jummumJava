package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotDealResponse extends BaseResponse {

    @SerializedName("data")
    private List<List<HotDealData>> hotDealData;

    public List<List<HotDealData>> getHotDealData() {
        return hotDealData;
    }
}