package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class DisputeResponse extends BaseResponse {
    @SerializedName("data")
    private List<List<DisputeResultData>> disputeResultData;

    public List<List<DisputeResultData>> getDisputeResultData() {
        return disputeResultData;
    }

}