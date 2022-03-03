package com.rightbrain.officeproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rightbrain.officeproject.model.modelProductDetails.Color;
import com.rightbrain.officeproject.model.modelProductDetails.Measurement;
import com.rightbrain.officeproject.model.sidebar.Gallery;
import com.rightbrain.officeproject.model.sidebar.Specification;

import java.util.List;

public class ModelProducts {

    public static final int GRID_VIEW_LAYOUT = 0;
    public static final int LIST_VIEW_LAYOUT = 1;
    public static final int LARGE_VIEW_LAYOUT = 2;

  /*  @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("content")
    @Expose
    private Integer content;

    @SerializedName("discountAmount")
    @Expose
    private Integer discountAmount;

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
    @SerializedName("maxQuantity")
    @Expose
    private Integer maxQuantity;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("keyword")
    @Expose
    private String keyword;


    @SerializedName("item_id")
    @Expose
    private Integer itemId;


    @SerializedName("discountType")
    @Expose
    private Object discountType;*/


    private int viewType;



    public ModelProducts(int viewType) {
        this.viewType = viewType;
    }


   /* public Integer getProductId() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }*/

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("discountPrice")
    @Expose
    private String discountPrice;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("brandId")
    @Expose
    private Integer brandId;
    @SerializedName("discountName")
    @Expose
    private String discountName;
    @SerializedName("discountType")
    @Expose
    private String discountType;
    @SerializedName("discountAmount")
    @Expose
    private String discountAmount;
    @SerializedName("unitName")
    @Expose
    private String unitName;
    @SerializedName("quantityApplicable")
    @Expose
    private Boolean quantityApplicable;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("subItemStatus")
    @Expose
    private Boolean subItemStatus;
    @SerializedName("maxQuantity")
    @Expose
    private Integer maxQuantity;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("measurement")
    @Expose
    private List<Measurement> measurement = null;
    @SerializedName("color")
    @Expose
    private List<Color> color = null;
    @SerializedName("specification")
    @Expose
    private List<Specification> specification = null;
    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = null;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSubItemStatus() {
        return subItemStatus;
    }

    public void setSubItemStatus(Boolean subItemStatus) {
        this.subItemStatus = subItemStatus;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Measurement> getMeasurement() {
        return measurement;
    }

    public void setMeasurement(List<Measurement> measurement) {
        this.measurement = measurement;
    }

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public List<Specification> getSpecification() {
        return specification;
    }

    public void setSpecification(List<Specification> specification) {
        this.specification = specification;
    }

    public List<Gallery> getGallery() {
        return gallery;
    }

    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

}
