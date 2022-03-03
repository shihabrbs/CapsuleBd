package com.rightbrain.officeproject.model.sidebar;

import com.google.gson.annotations.SerializedName;

public class BrandItem{

	@SerializedName("name")
	private String name;

	@SerializedName("brand_id")
	private int brandId;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setBrandId(int brandId){
		this.brandId = brandId;
	}

	public int getBrandId(){
		return brandId;
	}
}