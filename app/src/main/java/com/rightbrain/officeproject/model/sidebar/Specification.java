package com.rightbrain.officeproject.model.sidebar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specification {
    @SerializedName("metaId")
    @Expose
    private Integer metaId;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("value")
    @Expose
    private String value;

    public Integer getMetaId() {
        return metaId;
    }

    public void setMetaId(Integer metaId) {
        this.metaId = metaId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
