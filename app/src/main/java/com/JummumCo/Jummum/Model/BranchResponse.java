package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BranchResponse extends BaseResponse{

    @SerializedName("data")
    private List<BranchAndCustomerTableResponseResultData> data;

    public List<BranchAndCustomerTableResponseResultData> getData() {
        return data;
    }


}