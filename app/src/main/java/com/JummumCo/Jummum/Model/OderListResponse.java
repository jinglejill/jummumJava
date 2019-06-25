package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OderListResponse extends BaseResponse{

    @SerializedName("data")
    private List<OrderListResultData> orderListResultData;

    public List<OrderListResultData> getOrderListResultData() { return orderListResultData;
    }
}