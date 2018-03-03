package com.example.android.newsappabnd;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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
     @BindView(R.id.main_ProgressBar) ProgressBar progressBar;
     @BindView(R.id.main_emptyTextView) TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()){
                getLoaderManager().initLoader(LOADER_ID, null, this).forceLoad();
            }
            else {
                progressBar.setVisibility(View.GONE);
                emptyTextView.setText(R.string.message_noInternetConnection);
                emptyTextView.setVisibility(View.VISIBLE);
            }

        recyclerViewAdapter = new RecyclerViewAdapter(this, new ArrayList<NewsStory>());
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public Loader<List<NewsStory>> onCreateLoader(int id, Bundle args) {
        Uri baseUrl = Uri.parse(GUARDIAN_API_LINK);
        Uri.Builder builder = baseUrl.buildUpon();
        return new NewsLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<NewsStory>> loader, List<NewsStory> data) {
        progressBar.setVisibility(View.GONE);
        if (data.isEmpty()) {
            mainRecyclerView.setVisibility(View.GONE);
            emptyTextView.setText(R.string.message_noDataReturned);
            emptyTextView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerViewAdapter.updateAdapter(data);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<NewsStory>> loader) {
        mainRecyclerView.setAdapter(null);
    }

}

