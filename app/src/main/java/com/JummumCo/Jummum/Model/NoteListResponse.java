package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoteListResponse extends BaseResponse{

    @SerializedName("data")
    private List<NoteListResponseResultData> data;

    public List<NoteListResponseResultData> getData() {
        return data;
    }


}