package com.example.android.newsappabnd;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<NewsStory>> {

    public static final String GUARDIAN_API_LINK =
            "http://content.guardianapis.com/search";
    NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    public static final int LOADER_ID = 1;

     @BindView(R.id.main_RecyclerView) RecyclerView newsRecyclerView;
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

        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, new ArrayList<NewsStory>());
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(newsRecyclerViewAdapter);

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        newsRecyclerView.setItemAnimator(itemAnimator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_menu) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.about_menu) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<NewsStory>> onCreateLoader(int id, Bundle args) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String tag = sharedPreferences.getString(
                getString(R.string.settings_tag_key),
                getString(R.string.settings_tag_default));
        String date = sharedPreferences.getString(
                getString(R.string.settings_date_key),
                getString(R.string.settings_date_default));
        Uri baseUrl = Uri.parse(GUARDIAN_API_LINK);
        Uri.Builder builder = baseUrl.buildUpon();
        if (!TextUtils.equals(String.valueOf(tag), getString(R.string.tag_all) )) {
            builder.appendQueryParameter(getString(R.string.query_section), tag.toLowerCase());
        }
        builder.appendQueryParameter(getString(R.string.query_from_date), date);
        builder.appendQueryParameter(getString(R.string.query_order_by), getString(R.string.query_order_by_default));
        builder.appendQueryParameter(getString(R.string.query_api_key), getString(R.string.query_api_key_default));
        return new NewsLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<NewsStory>> loader, List<NewsStory> data) {
        progressBar.setVisibility(View.GONE);
        if (data == null || data.isEmpty()) {
            newsRecyclerView.setVisibility(View.GONE);
            emptyTextView.setText(R.string.message_noDataReturned);
            emptyTextView.setVisibility(View.VISIBLE);
        }
        else {
            newsRecyclerViewAdapter.updateAdapter(data);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<NewsStory>> loader) {
        newsRecyclerView.setAdapter(null);
    }

}

