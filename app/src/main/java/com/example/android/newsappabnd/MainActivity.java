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
        implements LoaderCallbacks<List<NewsStory>> {

    public static String GUARDIAN_API_LINK= "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    RecyclerViewAdapter recyclerViewAdapter;

    @BindView(R.id.main_RecyclerView) RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerViewAdapter = new RecyclerViewAdapter(this, new ArrayList<NewsStory>());
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(recyclerViewAdapter);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<NewsStory>> onCreateLoader(int id, Bundle args) {
        Uri baseUrl = Uri.parse(GUARDIAN_API_LINK);
        Uri.Builder builder = baseUrl.buildUpon();
        Log.i("Main", "onCreateLoader " + builder.toString());
        return new NewsLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<NewsStory>> loader, List<NewsStory> data) {
        mainRecyclerView.setAdapter(null);
        if (data != null && !data.isEmpty()){
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<NewsStory>> loader) {
        mainRecyclerView.setAdapter(null);

    }

}

