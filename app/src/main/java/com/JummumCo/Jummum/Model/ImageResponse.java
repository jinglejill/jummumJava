package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResponse extends BaseResponse{

    @SerializedName("data")
    private List<ImageResultData> imageResultData;

    public List<ImageResultData> getImageResultData() {
        return imageResultData;
    }
}