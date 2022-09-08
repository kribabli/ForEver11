package com.example.yoyoiq.CompletedMatchPOJO;

public class CompletedMatchPOJO {
    String status;
    String title;
    String status_note;
    String short_title;

    public CompletedMatchPOJO(String status, String title, String status_note, String short_title) {
        this.status = status;
        this.title = title;
        this.status_note = status_note;
        this.short_title = short_title;
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

    public String getStatus_note() {
        return status_note;
    }

    public void setStatus_note(String status_note) {
        this.status_note = status_note;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }
}
