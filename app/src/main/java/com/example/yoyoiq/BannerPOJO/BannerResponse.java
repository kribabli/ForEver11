package com.example.yoyoiq.BannerPOJO;

public class BannerResponse {
    public String id;
    public String image;

    public BannerResponse(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
