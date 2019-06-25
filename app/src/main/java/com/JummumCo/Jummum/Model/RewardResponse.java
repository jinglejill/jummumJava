package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RewardResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<RewardListResultData>> rewardListResultdata;

    public List<List<RewardListResultData>> getRewardListResultdata() {
        return rewardListResultdata;
    }
}