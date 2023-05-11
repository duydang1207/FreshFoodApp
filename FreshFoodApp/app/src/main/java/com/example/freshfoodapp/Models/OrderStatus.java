package com.example.freshfoodapp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class OrderStatus implements Serializable {
    @SerializedName("id")
    private Long id;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

    @SerializedName("status")
    private int status;

    @SerializedName("total_price")
    private BigDecimal total_price;

    @SerializedName("complete")
    private Boolean complete;

    @SerializedName("paymentComplete")
    private Date paymentComplete;

    @SerializedName("user")
    private User user;

    public OrderStatus(Long id, String address, String phone, int status, BigDecimal total_price, Boolean complete, Date paymentComplete, User user) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
