package com.example.yoyoiq.UpcommingReq;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("items")
    private List<ItemsItem> items;

    public void setItems(List<ItemsItem> items) {
        this.items = items;
    }

    public List<ItemsItem> getItems() {
        return items;
    }
}