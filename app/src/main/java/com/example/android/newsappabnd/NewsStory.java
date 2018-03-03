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

    //TODO add date. author

    private String mNewsStory;
    private String mSectionName;
    private String mPublicationDate;

    public NewsStory(String mNewsStory, String mSectionName, String mPublicationDate) {
        setStory(mNewsStory);
        setSectionName(mSectionName);
        setPublicationDate(mPublicationDate);
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

    public void setStory(String mNewsStory) {
        this.mNewsStory = mNewsStory;
    }

    public void setSectionName(String mSectionName) {
        this.mSectionName = mSectionName;
    }

    public void setPublicationDate(String mPublicationDate) {
        this.mPublicationDate = formatDate(mPublicationDate);
    }

    @Override
    public String toString() {
        return "NewsStory{" +
                "mNewsStory='" + this.mNewsStory + '\'' +
                "mSectionName='" + this.mSectionName + '\'' +
                "mPublicationDate='" + this.mPublicationDate + '\'' +
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
