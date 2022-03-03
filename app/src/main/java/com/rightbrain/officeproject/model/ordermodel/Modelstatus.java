package com.rightbrain.officeproject.model.ordermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modelstatus {
    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("invoice")
    @Expose
    private String invoice;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
