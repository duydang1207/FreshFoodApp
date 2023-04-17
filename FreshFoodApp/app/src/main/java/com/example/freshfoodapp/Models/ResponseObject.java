package com.example.freshfoodapp.Models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.Serializable;

public class ResponseObject<ObjectClass> implements Serializable {
    String status;
    String message;

    ObjectClass data;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    public void setData(ObjectClass data) {
        this.data = data;
    }
}
