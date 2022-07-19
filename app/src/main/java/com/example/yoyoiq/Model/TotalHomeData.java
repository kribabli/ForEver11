package com.example.yoyoiq.Model;

public class TotalHomeData {
    String title;
    String match_id;
    String logo_url_a;
    String name_a;
    String short_name_a;
    String logo_url_b;
    String name_b;
    String short_name_b;

    public TotalHomeData(String title, String match_id, String logo_url_a, String name_a, String short_name_a, String logo_url_b, String name_b, String short_name_b) {
        this.title = title;
        this.match_id = match_id;
        this.logo_url_a = logo_url_a;
        this.name_a = name_a;
        this.short_name_a = short_name_a;
        this.logo_url_b = logo_url_b;
        this.name_b = name_b;
        this.short_name_b = short_name_b;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getLogo_url_a() {
        return logo_url_a;
    }

    public void setLogo_url_a(String logo_url_a) {
        this.logo_url_a = logo_url_a;
    }

    public String getName_a() {
        return name_a;
    }

    public void setName_a(String name_a) {
        this.name_a = name_a;
    }

    public String getShort_name_a() {
        return short_name_a;
    }

    public void setShort_name_a(String short_name_a) {
        this.short_name_a = short_name_a;
    }

    public String getLogo_url_b() {
        return logo_url_b;
    }

    public void setLogo_url_b(String logo_url_b) {
        this.logo_url_b = logo_url_b;
    }

    public String getName_b() {
        return name_b;
    }

    public void setName_b(String name_b) {
        this.name_b = name_b;
    }

    public String getShort_name_b() {
        return short_name_b;
    }

    public void setShort_name_b(String short_name_b) {
        this.short_name_b = short_name_b;
    }
}
