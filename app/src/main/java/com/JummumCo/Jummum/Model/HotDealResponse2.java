package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotDealResponse2 extends BaseResponse {

    @SerializedName("data")
    private List<List<HotDealData>> hotDealData;
    private List<List<RewardListResultData>> rewardListResultdata;
    public List<List<HotDealData>> getHotDealData() {
        return hotDealData;
    }
    public List<List<RewardListResultData>> getrewardListResultdata() {
        return rewardListResultdata;
    }
}