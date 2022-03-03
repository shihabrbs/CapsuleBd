package com.rightbrain.officeproject.model.ordermodel;

import com.google.gson.annotations.SerializedName;
import com.rightbrain.officeproject.model.ModelCartRoom;

import java.util.List;

public class ModelOrder {

    @SerializedName("userinfo")
    private List<ModelUserInfo> userinfo;

    @SerializedName("paymentinfo")
    private List<ModelPaymentInfo> paymentinfo;

    @SerializedName("item")
    private List<ModelCartRoom> item;

    public List<ModelUserInfo> getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(List<ModelUserInfo> userinfo) {
        this.userinfo = userinfo;
    }

    public List<ModelPaymentInfo> getPaymentinfo() {
        return paymentinfo;
    }

    public void setPaymentinfo(List<ModelPaymentInfo> paymentinfo) {
        this.paymentinfo = paymentinfo;
    }

    public List<ModelCartRoom> getItem() {
        return item;
    }

    public void setItem(List<ModelCartRoom> item) {
        this.item = item;
    }
}

