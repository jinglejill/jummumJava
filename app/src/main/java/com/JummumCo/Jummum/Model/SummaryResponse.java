package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummaryResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<SummaryResponseResultData>> summaryResponseResultData;

    public List<List<SummaryResponseResultData>> getSummaryResponseResultData() {
        return summaryResponseResultData;
    }
}