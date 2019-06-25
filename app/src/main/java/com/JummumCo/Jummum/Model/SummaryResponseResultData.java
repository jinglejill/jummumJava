package com.JummumCo.Jummum.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class SummaryResponseResultData {

    @SerializedName("TotalAmount")
    @Expose
    private Integer totalAmount;
    @SerializedName("HasVoucherCodeList")
    @Expose
    private Boolean hasVoucherCodeList;
    @SerializedName("DiscountValue")
    @Expose
    private String discountValue;
    @SerializedName("PriceIncludeVat")
    @Expose
    private String priceIncludeVat;
    @SerializedName("TotalAfterDiscount")
    @Expose
    private String totalAfterDiscount;
    @SerializedName("ServiceChargePercent")
    @Expose
    private String serviceChargePercent;
    @SerializedName("ServiceChargeValue")
    @Expose
    private Double serviceChargeValue;
    @SerializedName("VatPercent")
    @Expose
    private String vatPercent;
    @SerializedName("VatValue")
    @Expose
    private Double vatValue;
    @SerializedName("NetTotal")
    @Expose
    private Double netTotal;
    @SerializedName("ShowBuffetButton")
    @Expose
    private int showBuffetButton;
    @SerializedName("OmisePublicKey")
    @Expose
    private String omisePublicKey;
    @SerializedName("SpecialPriceDiscount")
    @Expose
    private String specialPriceDiscount;
    @SerializedName("DiscountProgramValue")
    @Expose
    private String discountProgramValue;
    @SerializedName("DiscountPromoCodeValue")
    @Expose
    private String discountPromoCodeValue;
    @SerializedName("ShowVoucherListButton")
    @Expose
    private String showVoucherListButton;
    @SerializedName("AfterDiscount")
    @Expose
    private double afterDiscount;
    @SerializedName("LuckyDrawCount")
    @Expose
    private String luckyDrawCount;
    @SerializedName("BeforeVat")
    @Expose
    private double beforeVat;
    @SerializedName("PaymentMethod")
    @Expose
    private int paymentMethod;
    @SerializedName("StrPaymentMethod")
    @Expose
    private String strPaymentMethod;
    @SerializedName("CreditCardNo")
    @Expose
    private String creditCardNo;
    @SerializedName("ShowTotalAmount")
    @Expose
    private String showTotalAmount;
    @SerializedName("ShowSpecialPriceDiscount")
    @Expose
    private String showSpecialPriceDiscount;
    @SerializedName("ShowDiscountProgram")
    @Expose
    private String showDiscountProgram;
    @SerializedName("ApplyVoucherCode")
    @Expose
    private String applyVoucherCode;
    @SerializedName("ShowAfterDiscount")
    @Expose
    private String showAfterDiscount;
    @SerializedName("ShowServiceCharge")
    @Expose
    private String showServiceCharge;
    @SerializedName("ShowVat")
    @Expose
    private String showVat;
    @SerializedName("ShowNetTotal")
    @Expose
    private String showNetTotal;
    @SerializedName("ShowLuckyDrawCount")
    @Expose
    private String showLuckyDrawCount;
    @SerializedName("ShowBeforeVat")
    @Expose
    private String showBeforeVat;
    @SerializedName("ShowPayBuffetButton")
    @Expose
    private String showPayBuffetButton;
    @SerializedName("NoOfItem")
    @Expose
    private String noOfItem;
    @SerializedName("DiscountProgramTitle")
    @Expose
    private String discountProgramTitle;
    @SerializedName("PercentVat")
    @Expose
    private String percentVat;
    @SerializedName("SpecialPriceDiscountTitle")
    @Expose
    private String specialPriceDiscountTitle;
    @SerializedName("AfterDiscountTitle")
    @Expose
    private String afterDiscountTitle;
    @SerializedName("LuckyDrawTitle")
    @Expose
    private String luckyDrawTitle;
    @SerializedName("DiscountPromoCodeTitle")
    @Expose
    private String discountPromoCodeTitle;



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



    @SerializedName(value = "customerTableID",alternate = {"CustomerTableID"})
    @Expose
    private int customerTableID;
    @SerializedName(value = "price",alternate = {"Price"})
    @Expose
    private int price;
    @SerializedName(value = "menuID",alternate = {"MenuID"})
    @Expose
    private int menuID;
    @SerializedName(value = "branchID",alternate = {"BranchID"})
    @Expose
    private int branchID;
    @SerializedName(value = "orderNo",alternate = {"OrderNo"})
    @Expose
    private int orderNo;

    @SerializedName(value = "specialPrice",alternate = {"SpecialPrice"})
    @Expose
    private int specialPrice;
    @SerializedName(value = "receiptID",alternate = {"ReceiptID"})
    @Expose
    private int receiptID;
    @SerializedName(value = "takeAway",alternate = {"TakeAway"})
    @Expose
    private int takeAway;
    @SerializedName(value = "status",alternate = {"Status"})
    @Expose
    private int status;

    @SerializedName(value = "takeAwayPrice",alternate = {"TakeAwayPrice"})
    @Expose
    private int takeAwayPrice;
    @SerializedName(value = "notePrice",alternate = {"NotePrice"})
    @Expose
    private String notePrice;



    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getHasVoucherCodeList() {
        return hasVoucherCodeList;
    }

    public void setHasVoucherCodeList(Boolean hasVoucherCodeList) {
        this.hasVoucherCodeList = hasVoucherCodeList;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getPriceIncludeVat() {
        return priceIncludeVat;
    }

    public void setPriceIncludeVat(String priceIncludeVat) {
        this.priceIncludeVat = priceIncludeVat;
    }

    public String getTotalAfterDiscount() {
        return totalAfterDiscount;
    }

    public void setTotalAfterDiscount(String totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }

    public String getServiceChargePercent() {
        return serviceChargePercent;
    }

    public void setServiceChargePercent(String serviceChargePercent) {
        this.serviceChargePercent = serviceChargePercent;
    }

    public Double getServiceChargeValue() {
        return serviceChargeValue;
    }

    public void setServiceChargeValue(Double serviceChargeValue) {
        this.serviceChargeValue = serviceChargeValue;
    }

    public String getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(String vatPercent) {
        this.vatPercent = vatPercent;
    }

    public Double getVatValue() {
        return vatValue;
    }

    public void setVatValue(Double vatValue) {
        this.vatValue = vatValue;
    }

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }

    public int getShowBuffetButton() {
        return showBuffetButton;
    }

    public void setShowBuffetButton(int showBuffetButton) {
        this.showBuffetButton = showBuffetButton;
    }

    public String getOmisePublicKey() {
        return omisePublicKey;
    }

    public void setOmisePublicKey(String omisePublicKey) {
        this.omisePublicKey = omisePublicKey;
    }

    public String getSpecialPriceDiscount() {
        return specialPriceDiscount;
    }

    public void setSpecialPriceDiscount(String specialPriceDiscount) {
        this.specialPriceDiscount = specialPriceDiscount;
    }

    public String getDiscountProgramValue() {
        return discountProgramValue;
    }

    public void setDiscountProgramValue(String discountProgramValue) {
        this.discountProgramValue = discountProgramValue;
    }

    public String getDiscountPromoCodeValue() {
        return discountPromoCodeValue;
    }

    public void setDiscountPromoCodeValue(String discountPromoCodeValue) {
        this.discountPromoCodeValue = discountPromoCodeValue;
    }

    public String getShowVoucherListButton() {
        return showVoucherListButton;
    }

    public void setShowVoucherListButton(String showVoucherListButton) {
        this.showVoucherListButton = showVoucherListButton;
    }

    public double getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(double afterDiscount) {
        this.afterDiscount = afterDiscount;
    }

    public String getLuckyDrawCount() {
        return luckyDrawCount;
    }

    public void setLuckyDrawCount(String luckyDrawCount) {
        this.luckyDrawCount = luckyDrawCount;
    }

    public double getBeforeVat() {
        return beforeVat;
    }

    public void setBeforeVat(double beforeVat) {
        this.beforeVat = beforeVat;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStrPaymentMethod() {
        return strPaymentMethod;
    }

    public void setStrPaymentMethod(String strPaymentMethod) {
        this.strPaymentMethod = strPaymentMethod;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getShowTotalAmount() {
        return showTotalAmount;
    }

    public void setShowTotalAmount(String showTotalAmount) {
        this.showTotalAmount = showTotalAmount;
    }

    public String getShowSpecialPriceDiscount() {
        return showSpecialPriceDiscount;
    }

    public void setShowSpecialPriceDiscount(String showSpecialPriceDiscount) {
        this.showSpecialPriceDiscount = showSpecialPriceDiscount;
    }

    public String getShowDiscountProgram() {
        return showDiscountProgram;
    }

    public void setShowDiscountProgram(String showDiscountProgram) {
        this.showDiscountProgram = showDiscountProgram;
    }

    public String getApplyVoucherCode() {
        return applyVoucherCode;
    }

    public void setApplyVoucherCode(String applyVoucherCode) {
        this.applyVoucherCode = applyVoucherCode;
    }

    public String getShowAfterDiscount() {
        return showAfterDiscount;
    }

    public void setShowAfterDiscount(String showAfterDiscount) {
        this.showAfterDiscount = showAfterDiscount;
    }

    public String getShowServiceCharge() {
        return showServiceCharge;
    }

    public void setShowServiceCharge(String showServiceCharge) {
        this.showServiceCharge = showServiceCharge;
    }

    public String getShowVat() {
        return showVat;
    }

    public void setShowVat(String showVat) {
        this.showVat = showVat;
    }

    public String getShowNetTotal() {
        return showNetTotal;
    }

    public void setShowNetTotal(String showNetTotal) {
        this.showNetTotal = showNetTotal;
    }

    public String getShowLuckyDrawCount() {
        return showLuckyDrawCount;
    }

    public void setShowLuckyDrawCount(String showLuckyDrawCount) {
        this.showLuckyDrawCount = showLuckyDrawCount;
    }

    public String getShowBeforeVat() {
        return showBeforeVat;
    }

    public void setShowBeforeVat(String showBeforeVat) {
        this.showBeforeVat = showBeforeVat;
    }

    public String getShowPayBuffetButton() {
        return showPayBuffetButton;
    }

    public void setShowPayBuffetButton(String showPayBuffetButton) {
        this.showPayBuffetButton = showPayBuffetButton;
    }

    public String getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(String noOfItem) {
        this.noOfItem = noOfItem;
    }

    public String getDiscountProgramTitle() {
        return discountProgramTitle;
    }

    public void setDiscountProgramTitle(String discountProgramTitle) {
        this.discountProgramTitle = discountProgramTitle;
    }

    public String getPercentVat() {
        return percentVat;
    }

    public void setPercentVat(String percentVat) {
        this.percentVat = percentVat;
    }

    public String getSpecialPriceDiscountTitle() {
        return specialPriceDiscountTitle;
    }

    public void setSpecialPriceDiscountTitle(String specialPriceDiscountTitle) {
        this.specialPriceDiscountTitle = specialPriceDiscountTitle;
    }

    public String getAfterDiscountTitle() {
        return afterDiscountTitle;
    }

    public void setAfterDiscountTitle(String afterDiscountTitle) {
        this.afterDiscountTitle = afterDiscountTitle;
    }

    public String getLuckyDrawTitle() {
        return luckyDrawTitle;
    }

    public void setLuckyDrawTitle(String luckyDrawTitle) {
        this.luckyDrawTitle = luckyDrawTitle;
    }

    public String getDiscountPromoCodeTitle() {
        return discountPromoCodeTitle;
    }

    public void setDiscountPromoCodeTitle(String discountPromoCodeTitle) {
        this.discountPromoCodeTitle = discountPromoCodeTitle;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getCustomerTableID() {
        return customerTableID;
    }

    public void setCustomerTableID(int customerTableID) {
        this.customerTableID = customerTableID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public int getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(int takeAway) {
        this.takeAway = takeAway;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTakeAwayPrice() {
        return takeAwayPrice;
    }

    public void setTakeAwayPrice(int takeAwayPrice) {
        this.takeAwayPrice = takeAwayPrice;
    }

    public String getNotePrice() {
        return notePrice;
    }

    public void setNotePrice(String notePrice) {
        this.notePrice = notePrice;
    }
}