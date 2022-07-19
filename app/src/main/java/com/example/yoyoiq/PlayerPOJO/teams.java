package com.example.yoyoiq.PlayerPOJO;

public class teams {
    int tid;
    String title;
    String abbr;
    String thumb_url;
    String alt_name;
    String type;
    String logo_url;
    String country;
    String sex;

    public teams(int tid, String title, String abbr, String thumb_url, String alt_name, String type, String logo_url, String country, String sex) {
        this.tid = tid;
        this.title = title;
        this.abbr = abbr;
        this.thumb_url = thumb_url;
        this.alt_name = alt_name;
        this.type = type;
        this.logo_url = logo_url;
        this.country = country;
        this.sex = sex;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getAlt_name() {
        return alt_name;
    }

    public void setAlt_name(String alt_name) {
        this.alt_name = alt_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
