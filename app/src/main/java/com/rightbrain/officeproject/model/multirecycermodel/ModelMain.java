package com.rightbrain.officeproject.model.multirecycermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelMain {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    @SerializedName("category")
    @Expose
    private String category;




    public ModelMain(Integer id, String name, String imagePath, List<Product> products) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

