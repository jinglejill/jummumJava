package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Phitakphong on 23/5/2560.
 */

public class BaseResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("error")
    private String error;

    public boolean isSuccessStatus() {
        return success;
    }

    public String getError() {
        return error;
    }
}
