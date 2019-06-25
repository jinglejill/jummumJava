package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class CreateOrderNote {


    @SerializedName(value = "modifiedDate",alternate = {"ModifiedDate"})
    @Expose
    private String modifiedDate;
    @SerializedName(value = "noteID", alternate = {"NoteID"})
    @Expose
    private int noteID;
    @SerializedName(value = "orderTakingID", alternate = {"OrderTakingID"})
    @Expose
    private int orderTakingID;
    @SerializedName(value = "modifiedUser",alternate = {"ModifiedUser"})
    @Expose
    private String modifiedUser;
    @SerializedName(value = "quantity",alternate = {"Quantity"})
    @Expose
    private String quantity;


    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public int getOrderTakingID() {
        return orderTakingID;
    }

    public void setOrderTakingID(int orderTakingID) {
        this.orderTakingID = orderTakingID;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
