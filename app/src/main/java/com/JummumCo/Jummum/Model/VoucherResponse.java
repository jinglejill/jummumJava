package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VoucherResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<VoucherResultData>> data;

    public List<List<VoucherResultData>> getData() {
        return data;
    }
    
}