package com.example.yoyoiq.UpcommingReq;

import com.google.gson.annotations.SerializedName;

public class VenueItem {

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;

    @SerializedName("venue_id")
    private String venueId;

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getVenueId() {
        return venueId;
    }
}