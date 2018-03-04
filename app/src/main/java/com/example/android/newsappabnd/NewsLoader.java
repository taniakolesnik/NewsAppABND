package com.example.android.newsappabnd;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by tetianakolesnik on 01/03/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsStory>> {

    private String mUrl;

    public NewsLoader(Context context, String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public List<NewsStory> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<NewsStory> news = NewsUtil.fetchNewsData(mUrl, getContext());
        return news;
    }
}