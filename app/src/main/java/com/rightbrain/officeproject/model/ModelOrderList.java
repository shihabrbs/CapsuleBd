package com.rightbrain.officeproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelOrderList {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("invoice")
    @Expose
    private String invoice;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("updatedTime")
    @Expose
    private String updatedTime;
    @SerializedName("subTotal")
    @Expose
    private Integer subTotal;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("shippingCharge")
    @Expose
    private Integer shippingCharge;
    @SerializedName("vat")
    @Expose
    private Integer vat;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("timePeriod")
    @Expose
    private String timePeriod;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("process")
    @Expose
    private String process;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("transactionId")
    @Expose
    private Object transactionId;
    @SerializedName("paymentMobile")
    @Expose
    private Object paymentMobile;
    @SerializedName("deliveryDate")
    @Expose
    private String deliveryDate;
    @SerializedName("deliveryTime")
    @Expose
    private String deliveryTime;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("cashOnDelivery")
    @Expose
    private Integer cashOnDelivery;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Integer getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(Integer shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    public Integer getVat() {
        return vat;
    }

    public void setVat(Integer vat) {
        this.vat = vat;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Object transactionId) {
        this.transactionId = transactionId;
    }

    public Object getPaymentMobile() {
        return paymentMobile;
    }

    public void setPaymentMobile(Object paymentMobile) {
        this.paymentMobile = paymentMobile;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Integer cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

}
