package com.rightbrain.officeproject.model.sidebar;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SidebarResponse {

	@SerializedName("discount")
	private List<DiscountItem> discount;

	@SerializedName("tag")
	private List<Object> tag;

	@SerializedName("category")
	private List<CategoryItem> category;

	@SerializedName("brand")
	private List<BrandItem> brand;

	@SerializedName("promotion")
	private List<PromotionItem> promotion;

	public void setDiscount(List<DiscountItem> discount){
		this.discount = discount;
	}

	public List<DiscountItem> getDiscount(){
		return discount;
	}

	public void setTag(List<Object> tag){
		this.tag = tag;
	}

	public List<Object> getTag(){
		return tag;
	}

	public void setCategory(List<CategoryItem> category){
		this.category = category;
	}

	public List<CategoryItem> getCategory(){
		return category;
	}

	public void setBrand(List<BrandItem> brand){
		this.brand = brand;
	}

	public List<BrandItem> getBrand(){
		return brand;
	}

	public void setPromotion(List<PromotionItem> promotion){
		this.promotion = promotion;
	}

	public List<PromotionItem> getPromotion(){
		return promotion;
	}
}