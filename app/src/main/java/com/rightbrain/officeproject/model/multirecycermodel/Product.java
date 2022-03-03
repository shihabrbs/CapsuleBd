package com.rightbrain.officeproject.model.multirecycermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("quantity")
    @Expose
    private Object quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("discountPrice")
    @Expose
    private Object discountPrice;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("brandId")
    @Expose
    private Integer brandId;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("discountName")
    @Expose
    private Object discountName;
    @SerializedName("discountType")
    @Expose
    private Object discountType;
    @SerializedName("discountAmount")
    @Expose
    private Object discountAmount;
    @SerializedName("unitName")
    @Expose
    private String unitName;
    @SerializedName("quantityApplicable")
    @Expose
    private Boolean quantityApplicable;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("maxQuantity")
    @Expose
    private Integer maxQuantity;

    public Product(Integer productId, String name, Object quantity, String price, Object discountPrice,
                   Integer categoryId, String category, Integer brandId, String brand, Object discountName,
                   Object discountType, Object discountAmount, String unitName, Boolean quantityApplicable, String imagePath) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discountPrice = discountPrice;
        this.categoryId = categoryId;
        this.category = category;
        this.brandId = brandId;
        this.brand = brand;
        this.discountName = discountName;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.unitName = unitName;
        this.quantityApplicable = quantityApplicable;
        this.imagePath = imagePath;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Object getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Object discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Object getDiscountName() {
        return discountName;
    }

    public void setDiscountName(Object discountName) {
        this.discountName = discountName;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }

    public Object getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Object discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Boolean getQuantityApplicable() {
        return quantityApplicable;
    }

    public void setQuantityApplicable(Boolean quantityApplicable) {
        this.quantityApplicable = quantityApplicable;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
