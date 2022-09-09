package com.example.yoyoiq.CompletedMatchPOJO;

public class CompletedMatchPOJO {
    String match_id;
    String status;
    String title;
    String logo_url;
    String name;
    String short_name;
    String logo_urlB;
    String nameB;
    String short_nameB;

    public CompletedMatchPOJO(String match_id,String status, String title, String logo_url, String name, String short_name, String logo_urlB, String nameB, String short_nameB) {
        this.match_id = match_id;
        this.status = status;
        this.title = title;
        this.logo_url = logo_url;
        this.name = name;
        this.short_name = short_name;
        this.logo_urlB = logo_urlB;
        this.nameB = nameB;
        this.short_nameB = short_nameB;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLogo_urlB() {
        return logo_urlB;
    }

    public void setLogo_urlB(String logo_urlB) {
        this.logo_urlB = logo_urlB;
    }

    public String getNameB() {
        return nameB;
    }

    public void setNameB(String nameB) {
        this.nameB = nameB;
    }

    public String getShort_nameB() {
        return short_nameB;
    }

    public void setShort_nameB(String short_nameB) {
        this.short_nameB = short_nameB;
    }
}