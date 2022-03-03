package com.rightbrain.officeproject.model.modelProductDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Color {
    @SerializedName("colorId")
    @Expose
    private Integer colorId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("colorPlate")
    @Expose
    private String colorPlate;

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorPlate() {
        return colorPlate;
    }

    public void setColorPlate(String colorPlate) {
        this.colorPlate = colorPlate;
    }
}
