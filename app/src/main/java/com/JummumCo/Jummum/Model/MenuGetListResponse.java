package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuGetListResponse extends BaseResponse{

    @SerializedName("data")
    private List<List<MenuListResultData>> dataMenuList;
    public List<List<MenuListResultData>> getDataMenuList() {
        return dataMenuList;
    }
}