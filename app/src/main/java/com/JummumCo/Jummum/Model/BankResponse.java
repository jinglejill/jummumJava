package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class BankResponse extends BaseResponse {
    @SerializedName("data")
    private List<List<BankResultData>> bankResultData;

    public List<List<BankResultData>> getBankResultData() {
        return bankResultData;
    }

}