package com.example.ltdd_finalproject.models;

public class Account {
    private String accountId;
    private String accountType;
    private String customerId;
    private String staffId;

    public Account(String accountId, String accountType, String customerId, String staffId) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.customerId = customerId;
        this.staffId = staffId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
