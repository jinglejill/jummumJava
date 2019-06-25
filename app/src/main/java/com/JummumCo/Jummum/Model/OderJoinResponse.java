package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OderJoinResponse extends BaseResponse{

    @SerializedName("data")
    private List<OrderJoinResultData> orderJoinResultData;

    public List<OrderJoinResultData> getOrderJoinResultData() { return orderJoinResultData;
    }
}