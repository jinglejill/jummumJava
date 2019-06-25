package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BranchAndCustomerTableResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<BranchAndCustomerTableResponseResultData>> data;

    public List<List<BranchAndCustomerTableResponseResultData>> getData() {
        return data;
    }


}