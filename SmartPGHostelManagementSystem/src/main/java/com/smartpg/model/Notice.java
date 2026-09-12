package com.smartpg.model;

import java.sql.Date;

public class Notice {

    private int noticeId;
    private String title;
    private String description;
    private Date noticeDate;

    public Notice() {

    }

    public Notice(int noticeId, String title, String description, Date noticeDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.description = description;
        this.noticeDate = noticeDate;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }

}