package com.example.android.newsappabnd;

/**
 * Created by tetianakolesnik on 26/02/2018.
 */

public class NewsStory {

    private String mNewsStory;

    public NewsStory(String mNewsStory) {
        setStory(mNewsStory);
    }
    public String getNewsStory() {
        return this.mNewsStory;
    }

    public void setStory(String mNewsStory) {
        this.mNewsStory = mNewsStory;
    }

    @Override
    public String toString() {
        return "NewsStory{" +
                "mNewsStory='" + this.mNewsStory + '\'' +
                '}';
    }

}
