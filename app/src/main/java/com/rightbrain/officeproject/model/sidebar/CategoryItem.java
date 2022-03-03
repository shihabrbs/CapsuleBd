package com.rightbrain.officeproject.model.sidebar;

import com.google.gson.annotations.SerializedName;

public class CategoryItem{

	@SerializedName("category_id")
	private int categoryId;

	@SerializedName("name")
	private String name;

	public void setCategoryId(int categoryId){
		this.categoryId = categoryId;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}