package com.example.yoyoiq.POJO;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("items")
	private List<ItemsItem> items;

	@SerializedName("total_items")
	private int totalItems;

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	public void setTotalItems(int totalItems){
		this.totalItems = totalItems;
	}

	public int getTotalItems(){
		return totalItems;
	}
}