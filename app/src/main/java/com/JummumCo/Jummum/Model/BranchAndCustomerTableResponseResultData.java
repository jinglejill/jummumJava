package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class BranchAndCustomerTableResponseResultData {

    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("DbName")
    @Expose
    private String dbName;
    @SerializedName("MainBranchID")
    @Expose
    private String mainBranchID;
    @SerializedName("BranchNo")
    @Expose
    private String branchNo;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Street")
    @Expose
    private String street;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("Province")
    @Expose
    private String province;
    @SerializedName("PostCode")
    @Expose
    private String postCode;
    @SerializedName("SubDistrictID")
    @Expose
    private String subDistrictID;
    @SerializedName("DistrictID")
    @Expose
    private String districtID;
    @SerializedName("ProvinceID")
    @Expose
    private String provinceID;
    @SerializedName("ZipCodeID")
    @Expose
    private String zipCodeID;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Map")
    @Expose
    private String map;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("TableNum")
    @Expose
    private String tableNum;
    @SerializedName("CustomerNumMax")
    @Expose
    private String customerNumMax;
    @SerializedName("EmployeePermanentNum")
    @Expose
    private String employeePermanentNum;
    @SerializedName(value = "Status", alternate = {"status"})
    @Expose
    private String status;
    @SerializedName("TakeAwayFee")
    @Expose
    private String takeAwayFee;
    @SerializedName("ServiceChargePercent")
    @Expose
    private String serviceChargePercent;
    @SerializedName("PercentVat")
    @Expose
    private String percentVat;
    @SerializedName("PriceIncludeVat")
    @Expose
    private String priceIncludeVat;
    @SerializedName("CustomerApp")
    @Expose
    private String customerApp;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("Remark")
    @Expose
    private String remark;
    @SerializedName("LedStatus")
    @Expose
    private String ledStatus;
    @SerializedName("ModifiedUser")
    @Expose
    private String modifiedUser;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("CustomerTableID")
    @Expose
    private String customerTableID;
    @SerializedName("TableName")
    @Expose
    private String tableName;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Color")
    @Expose
    private String color;
    @SerializedName("Zone")
    @Expose
    private String zone;
    @SerializedName(value = "OrderNo", alternate = {"orderNo"})
    @Expose
    private String orderNo;
    @SerializedName("Num")
    @Expose
    private String num;

    @SerializedName("CustomerTable")
    @Expose
    private List<BranchAndCustomerTableResponseResultData> customerTable = null;


    @SerializedName(value = "noteID", alternate = {"NoteID"})
    @Expose
    private String noteID;
    @SerializedName(value = "SaveOrderNoteID", alternate = {"saveOrderNoteID"})
    @Expose
    private String saveOrderNoteID;
    @SerializedName(value = "SaveOrderTakingID", alternate = {"saveOrderTakingID"})
    @Expose
    private String saveOrderTakingID;
    @SerializedName(value = "Quantity", alternate = {"quantity"})
    @Expose
    private String quantity;

    @SerializedName(value = "MenuID", alternate = {"menuID"})
    @Expose
    private String menuID;
    @SerializedName(value = "SpecialPrice", alternate = {"specialPrice"})
    @Expose
    private String specialPrice;
    @SerializedName(value = "Price", alternate = {"price"})
    @Expose
    private String price;
    @SerializedName(value = "TakeAway", alternate = {"takeAway"})
    @Expose
    private String takeAway;
    @SerializedName(value = "TakeAwayPrice", alternate = {"takeAwayPrice"})
    @Expose
    private String takeAwayPrice;
    @SerializedName(value = "NoteIDListInText", alternate = {"noteIDListInText"})
    @Expose
    private String noteIDListInText;
    @SerializedName(value = "NotePrice", alternate = {"notePrice"})
    @Expose
    private String notePrice;
    @SerializedName(value = "DiscountValue", alternate = {"discountValue"})
    @Expose
    private String discountValue;
    @SerializedName(value = "SaveReceiptID", alternate = {"saveReceiptID"})
    @Expose
    private String saveReceiptID;

    @SerializedName(value = "VoucherCode", alternate = {"voucherCode"})
    @Expose
    private String voucherCode;

    @SerializedName(value = "BuffetReceiptID", alternate = {"buffetReceiptID"})
    @Expose
    private String buffetReceiptID;

    @SerializedName(value = "WordAdd", alternate = {"wordAdd"})
    @Expose
    private String wordAdd;
    @SerializedName(value = "WordNo", alternate = {"wordNo"})
    @Expose
    private String wordNo;

    @SerializedName(value = "MenuTypeID", alternate = {"menuTypeID"})
    @Expose
    private String menuTypeID;

    @SerializedName(value = "Note", alternate = {"note"})
    @Expose
    private List<NoteListResponseResultData> notes = null;




    public List<BranchAndCustomerTableResponseResultData> getCustomerTable() {
        return customerTable;
    }

    public void setCustomerTable(List<BranchAndCustomerTableResponseResultData> customerTable) {
        this.customerTable = customerTable;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getMainBranchID() {
        return mainBranchID;
    }

    public void setMainBranchID(String mainBranchID) {
        this.mainBranchID = mainBranchID;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSubDistrictID() {
        return subDistrictID;
    }

    public void setSubDistrictID(String subDistrictID) {
        this.subDistrictID = subDistrictID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getZipCodeID() {
        return zipCodeID;
    }

    public void setZipCodeID(String zipCodeID) {
        this.zipCodeID = zipCodeID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public String getCustomerNumMax() {
        return customerNumMax;
    }

    public void setCustomerNumMax(String customerNumMax) {
        this.customerNumMax = customerNumMax;
    }

    public String getEmployeePermanentNum() {
        return employeePermanentNum;
    }

    public void setEmployeePermanentNum(String employeePermanentNum) {
        this.employeePermanentNum = employeePermanentNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTakeAwayFee() {
        return takeAwayFee;
    }

    public void setTakeAwayFee(String takeAwayFee) {
        this.takeAwayFee = takeAwayFee;
    }

    public String getServiceChargePercent() {
        return serviceChargePercent;
    }

    public void setServiceChargePercent(String serviceChargePercent) {
        this.serviceChargePercent = serviceChargePercent;
    }

    public String getPercentVat() {
        return percentVat;
    }

    public void setPercentVat(String percentVat) {
        this.percentVat = percentVat;
    }

    public String getPriceIncludeVat() {
        return priceIncludeVat;
    }

    public void setPriceIncludeVat(String priceIncludeVat) {
        this.priceIncludeVat = priceIncludeVat;
    }

    public String getCustomerApp() {
        return customerApp;
    }

    public void setCustomerApp(String customerApp) {
        this.customerApp = customerApp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLedStatus() {
        return ledStatus;
    }

    public void setLedStatus(String ledStatus) {
        this.ledStatus = ledStatus;
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

    public String getCustomerTableID() {
        return customerTableID;
    }

    public void setCustomerTableID(String customerTableID) {
        this.customerTableID = customerTableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getSaveOrderNoteID() {
        return saveOrderNoteID;
    }

    public void setSaveOrderNoteID(String saveOrderNoteID) {
        this.saveOrderNoteID = saveOrderNoteID;
    }

    public String getSaveOrderTakingID() {
        return saveOrderTakingID;
    }

    public void setSaveOrderTakingID(String saveOrderTakingID) {
        this.saveOrderTakingID = saveOrderTakingID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID;
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

    public String getTakeAwayPrice() {
        return takeAwayPrice;
    }

    public void setTakeAwayPrice(String takeAwayPrice) {
        this.takeAwayPrice = takeAwayPrice;
    }

    public String getNoteIDListInText() {
        return noteIDListInText;
    }

    public void setNoteIDListInText(String noteIDListInText) {
        this.noteIDListInText = noteIDListInText;
    }

    public String getNotePrice() {
        return notePrice;
    }

    public void setNotePrice(String notePrice) {
        this.notePrice = notePrice;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getSaveReceiptID() {
        return saveReceiptID;
    }

    public void setSaveReceiptID(String saveReceiptID) {
        this.saveReceiptID = saveReceiptID;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getBuffetReceiptID() {
        return buffetReceiptID;
    }

    public void setBuffetReceiptID(String buffetReceiptID) {
        this.buffetReceiptID = buffetReceiptID;
    }

    public String getWordAdd() {
        return wordAdd;
    }

    public void setWordAdd(String wordAdd) {
        this.wordAdd = wordAdd;
    }

    public String getWordNo() {
        return wordNo;
    }

    public void setWordNo(String wordNo) {
        this.wordNo = wordNo;
    }

    public String getMenuTypeID() {
        return menuTypeID;
    }

    public void setMenuTypeID(String menuTypeID) {
        this.menuTypeID = menuTypeID;
    }

    public List<NoteListResponseResultData> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteListResponseResultData> notes) {
        this.notes = notes;
    }
}