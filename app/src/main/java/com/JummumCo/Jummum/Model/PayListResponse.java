package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PayListResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<PayResponseResultData>> payResponseResultData;

    public List<List<PayResponseResultData>> getPayResponseResultData() { return payResponseResultData;
    }
}