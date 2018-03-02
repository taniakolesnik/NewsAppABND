package com.example.android.newsappabnd;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by tetianakolesnik on 01/03/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsStory>> {

    public static final String LOG_TAG = NewsLoader.class.getName();

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
    public List<NewsStory> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<NewsStory> news = FetchNews.fetchNewsData(mUrl);
        Log.i(LOG_TAG, "news " + news);
        return news;
    }
}