package com.example.android.newsappabnd;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<NewsStory>> {

    public static final String GUARDIAN_API_LINK = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    RecyclerViewAdapter recyclerViewAdapter;
    public static final int LOADER_ID = 1;

     @BindView(R.id.main_RecyclerView) RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerViewAdapter = new RecyclerViewAdapter(this, new ArrayList<NewsStory>());
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(recyclerViewAdapter);
        getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<List<NewsStory>> onCreateLoader(int id, Bundle args) {
        Uri baseUrl = Uri.parse(GUARDIAN_API_LINK);
        Uri.Builder builder = baseUrl.buildUpon();
        return new NewsLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<NewsStory>> loader, List<NewsStory> data) {
        recyclerViewAdapter.updateAdapter(data);
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<NewsStory>> loader) {
        mainRecyclerView.setAdapter(null);
    }

}

