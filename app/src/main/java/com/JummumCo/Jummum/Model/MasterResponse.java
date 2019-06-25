package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MasterResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<MasterResultData>> masterResultData;

    public List<List<MasterResultData>> getMasterResultData() {
        return masterResultData;
    }
}