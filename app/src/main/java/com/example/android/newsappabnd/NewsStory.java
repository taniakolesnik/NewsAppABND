package com.example.android.newsappabnd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tetianakolesnik on 26/02/2018.
 */

public class NewsStory {

    private String mNewsStory;
    private String mSectionName;
    private String mPublicationDate;
    private String mAuthor;

    public NewsStory(String mNewsStory, String mSectionName, String mPublicationDate, String mAuthor) {
        setStory(mNewsStory);
        setSectionName(mSectionName);
        setPublicationDate(mPublicationDate);
        setAuthor(mAuthor);
    }
    public String getNewsStory() {
        return this.mNewsStory;
    }

    public String getSectionName() {
        return this.mSectionName;
    }

    public String getPublicationDate() {
        return this.mPublicationDate;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public void setStory(String mNewsStory) {
        this.mNewsStory = mNewsStory;
    }

    public void setSectionName(String mSectionName) {
        this.mSectionName = mSectionName;
    }

    public void setPublicationDate(String mPublicationDate) {
        this.mPublicationDate = formatDate(mPublicationDate);
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    @Override
    public String toString() {
        return "NewsStory{" +
                "mNewsStory='" + this.mNewsStory + '\'' +
                "mSectionName='" + this.mSectionName + '\'' +
                "mPublicationDate='" + this.mPublicationDate + '\'' +
                "mAuthor='" + this.mAuthor + '\'' +
                '}';
    }

    private static String formatDate (String mPublicationDate){
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH); // 2017-11-03T07:00:17Z
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH); // 03 March 2018 07:00
        Date date = null;
        try {
            date = originalFormat.parse(mPublicationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }

}
