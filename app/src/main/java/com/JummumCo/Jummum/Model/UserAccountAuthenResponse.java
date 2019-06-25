package com.JummumCo.Jummum.Model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserAccountAuthenResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<UserAccountAuthenResultData>> userAccountAuthenResultData;

    public List<List<UserAccountAuthenResultData>> getUserAccountAuthenResultData() {
        return userAccountAuthenResultData;
    }
}