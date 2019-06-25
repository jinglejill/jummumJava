package com.JummumCo.Jummum.Model;

import org.parceler.Parcel;

@Parcel
public class CreditCardResultData {
    private String fname;
    private String lname;
    private String year;
    private String month;
    private String secut;
    private String creditType;
    private String numCredit;

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
}