package com.example.android.newsappabnd;

/**
 * Created by tetianakolesnik on 26/02/2018.
 */

public class NewsStory {

    //TODO add date. author

    private String mNewsStory;
    private String mSectionName;

    public NewsStory(String mNewsStory, String mSectionName) {
        setStory(mNewsStory);
        setSectionName(mSectionName);
    }
    public String getNewsStory() {
        return this.mNewsStory;
    }

    public String getSectionName() {
        return this.mSectionName;
    }

    public void setStory(String mNewsStory) {
        this.mNewsStory = mNewsStory;
    }

    public void setSectionName(String mSectionName) {
        this.mSectionName = mSectionName;
    }

    @Override
    public String toString() {
        return "NewsStory{" +
                "mNewsStory='" + this.mNewsStory + '\'' +
                "mSectionName='" + this.mSectionName + '\'' +
                '}';
    }
}
