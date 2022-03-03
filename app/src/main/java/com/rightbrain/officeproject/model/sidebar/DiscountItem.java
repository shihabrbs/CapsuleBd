package com.rightbrain.officeproject.model.sidebar;

import com.google.gson.annotations.SerializedName;

public class DiscountItem{

	@SerializedName("imagePath")
	private String imagePath;

	@SerializedName("name")
	private String name;

	@SerializedName("discount_id")
	private int discountId;

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

	public void setDiscountId(int discountId){
		this.discountId = discountId;
	}

	public int getDiscountId(){
		return discountId;
	}
}