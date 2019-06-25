package com.JummumCo.Jummum.Model;

import android.provider.BaseColumns;

import org.parceler.Parcel;

import java.util.List;

public class CreditUserData {

    //Database
    public static final String DATABASE_NAME = "jummum.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "creditcard";

    public class Column {
        public static final String id = BaseColumns._ID;
        public static final String membeerId = "membeerId";
        public static final String fname = "fname";
        public static final String lname = "lname";
        public static final String year = "year";
        public static final String month = "month";
        public static final String secut = "secut";
        public static final String creditType = "creditType";
        public static final String numCredit = "numCredit";
    }

    private int id;
    private String membeerId;
    private String fname;
    private String lname;
    private String year;
    private String month;
    private String secut;
    private String creditType;
    private String numCredit;


    public CreditUserData(){

    }

    public CreditUserData(int id,String fname,String lname,String year,String month,String secut,String creditType,String numCredit,String memberId){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.year = year;
        this.month = month;
        this.secut = secut;
        this.creditType = creditType;
        this.numCredit = numCredit;
        this.membeerId = memberId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSecut() {
        return secut;
    }

    public void setSecut(String secut) {
        this.secut = secut;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getNumCredit() {
        return numCredit;
    }

    public void setNumCredit(String numCredit) {
        this.numCredit = numCredit;
    }

    public String getMembeerId() {
        return membeerId;
    }

    public void setMembeerId(String membeerId) {
        this.membeerId = membeerId;
    }
}
