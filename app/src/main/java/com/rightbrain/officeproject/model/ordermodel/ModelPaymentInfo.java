package com.rightbrain.officeproject.model.ordermodel;

import com.google.gson.annotations.SerializedName;

public class ModelPaymentInfo {
   /* @SerializedName("accountno")
    private String accountno;

    @SerializedName("paymentmobile")
    private String paymentmobile;

    @SerializedName("trxid")
    private String trxid;

    @SerializedName("preferent")
    private String preferent;

    @SerializedName("subtotal")
    private String subtotal;

    @SerializedName("deliverycharge")
    private String deliverycharge;

    @SerializedName("total")
    private String total;*/

    @SerializedName("id")
    private String id;

    @SerializedName("userId")
    private String userId;

    @SerializedName("subTotal")
    private String subTotal;

    @SerializedName("discount")
    private String discount;

    @SerializedName("vat")
    private String vat;

    @SerializedName("total")
    private String total;

    @SerializedName("shippingCharge")
    private String shippingCharge;

    @SerializedName("couponCode")
    private String couponCode;

    @SerializedName("timePeriod")
    private String timePeriod;

    @SerializedName("deliveryDate")
    private String deliveryDate;

    @SerializedName("transactionMethod")
    private String transactionMethod;

    @SerializedName("discountCoupon")
    private String discountCoupon;

    @SerializedName("bankAccount")
    private String bankAccount;

    @SerializedName("receiveAccount")
    private String receiveAccount;

    @SerializedName("paymentMobile")
    private String paymentMobile;

    @SerializedName("paymentCard")
    private String paymentCard;

    @SerializedName("paymentCardNo")
    private String paymentCardNo;

    @SerializedName("transactionId")
    private String transactionId;

    @SerializedName("comment")
    private String comment;

    @SerializedName("remark")
    private String remark;

    @SerializedName("cashOnDelivery")
    private String cashOnDelivery;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(String shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(String transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public String getDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(String discountCoupon) {
        this.discountCoupon = discountCoupon;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getMobileBankAccount() {
        return receiveAccount;
    }

    public void setMobileBankAccount(String receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    public String getPaymentMobile() {
        return paymentMobile;
    }

    public void setPaymentMobile(String paymentMobile) {
        this.paymentMobile = paymentMobile;
    }

    public String getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(String paymentCard) {
        this.paymentCard = paymentCard;
    }

    public String getPaymentCardNo() {
        return paymentCardNo;
    }

    public void setPaymentCardNo(String paymentCardNo) {
        this.paymentCardNo = paymentCardNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(String cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }
}
