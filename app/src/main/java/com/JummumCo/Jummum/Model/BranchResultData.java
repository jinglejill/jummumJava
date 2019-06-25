package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class BranchResultData {
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
    @SerializedName("Status")
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
}