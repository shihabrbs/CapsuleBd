package com.rightbrain.officeproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelBrand {
    @SerializedName("brand_id")
    @Expose
    private Integer brandId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
