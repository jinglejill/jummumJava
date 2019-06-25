package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Bitmap;


@Parcel
public class MenuListResultData {

    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("MenuID")
    @Expose
    private String menuID;
    @SerializedName("MenuCode")
    @Expose
    private String menuCode;
    @SerializedName("TitleThai")
    @Expose
    private String titleThai;

    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("MenuTypeID")
    @Expose
    private String menuTypeID;
    @SerializedName("SubMenuTypeID")
    @Expose
    private String subMenuTypeID;
    @SerializedName("SubMenuType2ID")
    @Expose
    private String subMenuType2ID;
    @SerializedName("SubMenuType3ID")
    @Expose
    private String subMenuType3ID;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("Color")
    @Expose
    private String color;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Remark")
    @Expose
    private String remark;
    @SerializedName("ModifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("AllowDiscount")
    @Expose
    private String allowDiscount;

    @SerializedName("qty")
    @Expose
    private int qty;

    @SerializedName("imageLoaded")
    @Expose
    private int imageLoaded;

    @SerializedName("imageBitmap")
    @Expose
    private Bitmap imageBitmap;

    @SerializedName("SpecialPrice")
    @Expose
    private String SpecialPrice;

    @SerializedName("NameEn")
    @Expose
    private String nameEn;

    @SerializedName("LuckyDrawSpend")
    @Expose
    private String luckyDrawSpend;

    @SerializedName("MenuImageFolder")
    @Expose
    private String menuImageFolder;

    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("BuffetMenu")
    @Expose
    private String buffetMenu;
    @SerializedName("0")
    @Expose
    private String menutyp0;

    @SerializedName("AlacarteMenu")
    @Expose
    private String alacarteMenu;
    @SerializedName("TimeToOrder")
    @Expose
    private String timeToOrder;

    private List<List<NoteListResponseResultData>> noteList = null;

    @SerializedName("takeAway")
    @Expose
    private List<NoteListResponseResultData> takeAway = null;

    @SerializedName("GoToPayOrMenu")
    @Expose
    private String goToPayOrMenu;


    public String getMenutyp0() {
        return menutyp0;
    }

    public void setMenutyp0(String menutyp0) {
        this.menutyp0 = menutyp0;
    }

    private List<Integer> takeawayIndex = new ArrayList<>();

    public List<Integer> getTakeawayIndex() {
        return takeawayIndex;
    }

    public void setTakeawayIndex(List<Integer> takeawayIndex) {
        this.takeawayIndex = takeawayIndex;
    }


    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public List<NoteListResponseResultData> getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(List<NoteListResponseResultData> takeAway) {
        this.takeAway = takeAway;
    }

    private boolean expand;

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public List<List<NoteListResponseResultData>> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<List<NoteListResponseResultData>> noteList) {
        this.noteList = noteList;
    }

    public String getLuckyDrawSpend() {
        return luckyDrawSpend;
    }

    public void setLuckyDrawSpend(String luckyDrawSpend) {
        this.luckyDrawSpend = luckyDrawSpend;
    }

    public String getMenuImageFolder() {
        return menuImageFolder;
    }

    public void setMenuImageFolder(String menuImageFolder) {
        this.menuImageFolder = menuImageFolder;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getTitleThai() {
        return titleThai;
    }

    public void setTitleThai(String titleThai) {
        this.titleThai = titleThai;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenuTypeID() {
        return menuTypeID;
    }

    public void setMenuTypeID(String menuTypeID) {
        this.menuTypeID = menuTypeID;
    }

    public String getSubMenuTypeID() {
        return subMenuTypeID;
    }

    public void setSubMenuTypeID(String subMenuTypeID) {
        this.subMenuTypeID = subMenuTypeID;
    }

    public String getSubMenuType2ID() {
        return subMenuType2ID;
    }

    public void setSubMenuType2ID(String subMenuType2ID) {
        this.subMenuType2ID = subMenuType2ID;
    }

    public String getSubMenuType3ID() {
        return subMenuType3ID;
    }

    public void setSubMenuType3ID(String subMenuType3ID) {
        this.subMenuType3ID = subMenuType3ID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllowDiscount() {
        return allowDiscount;
    }

    public void setAllowDiscount(String allowDiscount) {
        this.allowDiscount = allowDiscount;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getImageLoaded() {
        return imageLoaded;
    }
    public void setImageLoaded(int imageLoaded) {
        this.imageLoaded = imageLoaded;
    }


    public Bitmap getImageBitmap() {
        return imageBitmap;
    }
    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }


    public String getSpecialPrice() {
        return SpecialPrice;
    }

    public void setSpecialPrice(String specialPrice) {
        SpecialPrice = specialPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBuffetMenu() {
        return buffetMenu;
    }

    public void setBuffetMenu(String buffetMenu) {
        this.buffetMenu = buffetMenu;
    }

    public String getAlacarteMenu() {
        return alacarteMenu;
    }

    public void setAlacarteMenu(String alacarteMenu) {
        this.alacarteMenu = alacarteMenu;
    }

    public String getTimeToOrder() {
        return timeToOrder;
    }

    public void setTimeToOrder(String timeToOrder) {
        this.timeToOrder = timeToOrder;
    }

    public String getGoToPayOrMenu() {
        return goToPayOrMenu;
    }

    public void setGoToPayOrMenu(String goToPayOrMenu) {
        this.goToPayOrMenu = goToPayOrMenu;
    }
}