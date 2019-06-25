package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class OrderTaking2ResultData {
    @SerializedName("OrderTakingID")
    @Expose
    private String orderTakingID;
    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("CustomerTableID")
    @Expose
    private String customerTableID;
    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("SpecialPrice")
    @Expose
    private String specialPrice;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("TakeAway")
    @Expose
    private String takeAway;
    @SerializedName("TakeAwayPrice")
    @Expose
    private int takeAwayPrice;
    @SerializedName("NotePrice")
    @Expose
    private int notePrice;
    @SerializedName("NoteIDListInText")
    @Expose
    private String noteIDListInText;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("ReceiptID")
    @Expose
    private String receiptID;
    @SerializedName("ModifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("Menu")
    @Expose
    private List<MenuListResultData> menu;
    @SerializedName("Note")
    @Expose
    private List<NoteListResponseResultData> notes = null;

    @SerializedName("MenuType")
    @Expose
    private MenuTypeListResultData menuType;
//    @SerializedName("OrderNote")
//    @Expose
//    private List<Object> orderNote = null;


    public int getTakeAwayPrice() {
        return takeAwayPrice;
    }

    public int getNotePrice() {
        return notePrice;
    }

    public void setNotePrice(int notePrice) {
        this.notePrice = notePrice;
    }

    public void setTakeAwayPrice(int takeAwayPrice) {
        this.takeAwayPrice = takeAwayPrice;
    }

    public String getOrderTakingID() {
        return orderTakingID;
    }

    public void setOrderTakingID(String orderTakingID) {
        this.orderTakingID = orderTakingID;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getCustomerTableID() {
        return customerTableID;
    }

    public void setCustomerTableID(String customerTableID) {
        this.customerTableID = customerTableID;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(String specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(String takeAway) {
        this.takeAway = takeAway;
    }

    public String getNoteIDListInText() {
        return noteIDListInText;
    }

    public void setNoteIDListInText(String noteIDListInText) {
        this.noteIDListInText = noteIDListInText;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
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

    public List<MenuListResultData> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuListResultData> menu) {
        this.menu = menu;
    }

    public MenuTypeListResultData getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuTypeListResultData menuType) {
        this.menuType = menuType;
    }

    //    public List<Object> getOrderNote() {
//        return orderNote;
//    }
//
//    public void setOrderNote(List<Object> orderNote) {
//        this.orderNote = orderNote;
//    }
    public List<NoteListResponseResultData> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteListResponseResultData> notes) {
        this.notes = notes;
    }
}