package com.appdev.basedintent;

import java.util.Date;
import java.util.UUID;

public class Based {

    private String mTitle;
    public UUID mId;
    private Date mDate;
    private boolean mBased;

    public Based() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public boolean isBased() {
        return mBased;
    }

    public void setBased(boolean based) {
        this.mBased = based;
    }
}
