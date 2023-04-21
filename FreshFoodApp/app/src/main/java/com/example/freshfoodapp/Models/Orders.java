package com.example.freshfoodapp.Models;

import java.math.BigDecimal;
import java.sql.Date;

public class Orders {
    Long id;

    String address;

    String phone;

    int status;

    BigDecimal total_price;

    Boolean complete;

    Date paymentComplete;

    Account user;

    public Orders(Long id, String address, String phone, int status, BigDecimal total_price, Boolean complete, Date paymentComplete, Account user) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.total_price = total_price;
        this.complete = complete;
        this.paymentComplete = paymentComplete;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Date getPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(Date paymentComplete) {
        this.paymentComplete = paymentComplete;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
}
