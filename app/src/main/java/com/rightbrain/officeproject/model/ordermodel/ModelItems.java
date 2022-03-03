package com.rightbrain.officeproject.model.ordermodel;

import com.google.gson.annotations.SerializedName;

public class ModelItems {

    @SerializedName("id")
    private String id;

    @SerializedName("itemId")
    private String itemId;

    @SerializedName("orderId")
    private String orderId;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("size")
    private String size;

    @SerializedName("color")
    private String color;

    @SerializedName("subTotal")
    private String subTotal;

    @SerializedName("url")
    private String url;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
