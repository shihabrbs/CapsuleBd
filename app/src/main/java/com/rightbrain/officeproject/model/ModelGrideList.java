package com.rightbrain.officeproject.model;

import java.util.List;

public class ModelGrideList {

    public static final int GRID_VIEW_LAYOUT = 0;
    public static final int LIST_VIEW_LAYOUT = 1;
    private int viewType;

    String name;


    private List<ModelProducts> products = null;

/*
    public ModelGrideList(int viewType, List<ModelProducts> products) {
        this.viewType = viewType;
        this.products = products;
    }*/

    public ModelGrideList(int viewType, String name) {
        this.viewType = viewType;
        this.name = name;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public List<ModelProducts> getProducts() {
        return products;
    }

    public void setProducts(List<ModelProducts> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
