package com.rightbrain.officeproject.model.ordermodel;

import com.google.gson.annotations.SerializedName;
import com.rightbrain.officeproject.model.ModelCartRoom;

import java.util.List;

public class Modeljson {
    @SerializedName("phone")
    private String phone;

    @SerializedName("message")
    private String message;

    @SerializedName("item")
    private List<ModelCartRoom> item;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<ModelCartRoom> getItem() {
        return item;
    }

    public void setItem(List<ModelCartRoom> item) {
        this.item = item;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
