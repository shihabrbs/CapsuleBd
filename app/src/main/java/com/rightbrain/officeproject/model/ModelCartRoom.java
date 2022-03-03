package com.rightbrain.officeproject.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cartitem")
public class ModelCartRoom {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String price;
    String quantity;
    String url;
    String size;
    String color;
    String productId;


    public ModelCartRoom(){

    }

    public ModelCartRoom(String p_name, String p_price, String quantity, String url, String size,String color,String productId) {
        this.name = p_name;
        this.price = p_price;
        this.quantity = quantity;
        this.url = url;
        this.size = size;
        this.color = color;
        this.productId = productId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
