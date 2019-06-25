package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class SaveOrderNote {

    @SerializedName("noteID")
    @Expose
    private Integer noteID;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("modifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("saveOrderNoteID")
    @Expose
    private Integer saveOrderNoteID;
    @SerializedName("modifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("saveOrderTakingID")
    @Expose
    private Integer saveOrderTakingID;

    public Integer getNoteID() {
        return noteID;
    }

    public void setNoteID(Integer noteID) {
        this.noteID = noteID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getSaveOrderNoteID() {
        return saveOrderNoteID;
    }

    public void setSaveOrderNoteID(Integer saveOrderNoteID) {
        this.saveOrderNoteID = saveOrderNoteID;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public Integer getSaveOrderTakingID() {
        return saveOrderTakingID;
    }

    public void setSaveOrderTakingID(Integer saveOrderTakingID) {
        this.saveOrderTakingID = saveOrderTakingID;
    }

}