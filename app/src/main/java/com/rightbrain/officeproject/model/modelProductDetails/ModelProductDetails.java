package com.rightbrain.officeproject.model.modelProductDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelProductDetails {
    @SerializedName("productDetails")
    @Expose
    private ProductDetails productDetails;
    @SerializedName("relatedProduct")
    @Expose
    private List<RelatedProduct> relatedProduct = null;

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public List<RelatedProduct> getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(List<RelatedProduct> relatedProduct) {
        this.relatedProduct = relatedProduct;
    }
}
