package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class NoteListResponseResultData {

    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("NoteID")
    @Expose
    private String noteID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("NameEn")
    @Expose
    private String nameEn;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("NoteTypeID")
    @Expose
    private String noteTypeID;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("Prefix")
    @Expose
    private String prefix;

    @SerializedName("NoteAdd")
    @Expose
    private List<NoteListResponseResultData> noteAdd = null;
    @SerializedName("NoteRemove")
    @Expose
    private List<NoteListResponseResultData> noteRemove = null;
    @SerializedName("takeAway")
    @Expose
    private String takeAway;

    @SerializedName(value = "Quantity",alternate = {"quantity"})
    @Expose
    private String quantity;

    @SerializedName(value = "AllowQuantity",alternate = {"allowQuantity"})
    @Expose
    private String allowQuantity;


    public String getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(String takeAway) {
        this.takeAway = takeAway;
    }

    public List<NoteListResponseResultData> getNoteAdd() {
        return noteAdd;
    }

    public void setNoteAdd(List<NoteListResponseResultData> noteAdd) {
        this.noteAdd = noteAdd;
    }

    public List<NoteListResponseResultData> getNoteRemove() {
        return noteRemove;
    }

    public void setNoteRemove(List<NoteListResponseResultData> noteRemove) {
        this.noteRemove = noteRemove;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNoteTypeID() {
        return noteTypeID;
    }

    public void setNoteTypeID(String noteTypeID) {
        this.noteTypeID = noteTypeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAllowQuantity() {
        return allowQuantity;
    }

    public void setAllowQuantity(String allowQuantity) {
        this.allowQuantity = allowQuantity;
    }
}