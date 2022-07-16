package com.example.yoyoiq.NewPoJo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseClass {
    @SerializedName("items")
    private List<Items> items;

    public ResponseClass(List<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
