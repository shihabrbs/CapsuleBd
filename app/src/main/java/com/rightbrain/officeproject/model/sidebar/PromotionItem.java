package com.rightbrain.officeproject.model.sidebar;

import com.google.gson.annotations.SerializedName;

public class PromotionItem{

	@SerializedName("imagePath")
	private String imagePath;

	@SerializedName("name")
	private String name;

	@SerializedName("promotion_id")
	private int promotionId;

	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}

	public String getImagePath(){
		return imagePath;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPromotionId(int promotionId){
		this.promotionId = promotionId;
	}

	public int getPromotionId(){
		return promotionId;
	}
}