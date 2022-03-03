package com.rightbrain.officeproject.model.sidebar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Products implements Serializable {

    @SerializedName("product_id")
    @Expose
    private int productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("discountPrice")
    @Expose
    private String discountPrice;
    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("brandId")
    @Expose
    private int brandId;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("discountId")
    @Expose
    private String discountId;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("promotionId")
    @Expose
    private int promotionId;
    @SerializedName("promotion")
    @Expose
    private String promotion;
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
    private String quantityApplicable;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
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

    public String getQuantityApplicable() {
        return quantityApplicable;
    }

    public void setQuantityApplicable(String quantityApplicable) {
        this.quantityApplicable = quantityApplicable;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
