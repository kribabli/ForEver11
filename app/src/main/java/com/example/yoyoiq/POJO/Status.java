package com.example.yoyoiq.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Status {
    @SerializedName("status")
    private String status;

    @SerializedName("items")
    private List<ItemsItem> items;

    @SerializedName("response")
    private String response;

    public Status(String status, List<ItemsItem> items, String response) {
        this.status = status;
        this.items = items;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemsItem> getItems() {
        return items;
    }

    public void setItems(List<ItemsItem> items) {
        this.items = items;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
