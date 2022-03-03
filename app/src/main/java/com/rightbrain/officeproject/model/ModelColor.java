package com.rightbrain.officeproject.model;

public class ModelColor {
    String colorname;
    String colorcode;

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    public String getColorcode() {
        return colorcode;
    }

    public void setColorcode(String colorcode) {
        this.colorcode = colorcode;
    }

    public ModelColor(String colorname, String colorcode) {
        this.colorname = colorname;
        this.colorcode = colorcode;
    }
}
