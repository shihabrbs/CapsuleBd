package com.rightbrain.officeproject.model;

public class ModelDashboard {
    String name;
    int pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public ModelDashboard(String name, int pic) {
        this.name = name;
        this.pic = pic;
    }
}
