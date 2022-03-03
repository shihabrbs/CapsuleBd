package com.rightbrain.officeproject.model.modelProductDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedProduct {
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
    private Object brandId;
    @SerializedName("brand")
    @Expose
    private Object brand;
    @SerializedName("discountId")
    @Expose
    private Object discountId;
    @SerializedName("discount")
    @Expose
    private Object discount;
    @SerializedName("discountType")
    @Expose
    private Object discountType;
    @SerializedName("discountAmount")
    @Expose
    private Object discountAmount;
    @SerializedName("promotionId")
    @Expose
    private Object promotionId;
    @SerializedName("promotion")
    @Expose
    private Object promotion;
    @SerializedName("tagId")
    @Expose
    private Object tagId;
    @SerializedName("tag")
    @Expose
    private Object tag;
    @SerializedName("unitName")
    @Expose
    private String unitName;
    @SerializedName("quantityApplicable")
    @Expose
    private Boolean quantityApplicable;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;

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

    public Object getBrandId() {
        return brandId;
    }

    public void setBrandId(Object brandId) {
        this.brandId = brandId;
    }

    public Object getBrand() {
        return brand;
    }

    public void setBrand(Object brand) {
        this.brand = brand;
    }

    public Object getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Object discountId) {
        this.discountId = discountId;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
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

    public Object getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Object promotionId) {
        this.promotionId = promotionId;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public Object getTagId() {
        return tagId;
    }

    public void setTagId(Object tagId) {
        this.tagId = tagId;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
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
}
