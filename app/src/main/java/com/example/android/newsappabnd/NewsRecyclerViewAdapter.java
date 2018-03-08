package com.example.android.newsappabnd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetianakolesnik on 25/02/2018.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<NewsStory> mData;
    private String webLink;

    public NewsRecyclerViewAdapter(Context context, List<NewsStory> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public void updateAdapter(List<NewsStory> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    /**
     * This method is called right when the adapter is created and
     * is used to initialize your ViewHolder(s).
     *
     * @return new ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view, webLink);
    }

    /**
     * This method is called for each ViewHolder to bind it to the adapter.
     * This is where we will pass our data to our ViewHolder.
     */
    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.ViewHolder holder, int position) {
        NewsStory item = mData.get(position);
        holder.titleTextView.setText(item.getNewsStory());
        holder.sectionTextView.setText(item.getSectionName());
        holder.dateTextView.setText(item.getWebUrl());
        if (!item.getAuthor().isEmpty()){
            holder.authorTextView.setText(item.getAuthor());
            holder.authorTextView.setVisibility(View.VISIBLE);
        }
        webLink = item.getWebUrl();

    }

    /**
     * This method returns the size of the collection that contains the items we want to display
     * @return number of items to be listed
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public String webLink;
        @BindView(R.id.title_textView) TextView titleTextView;
        @BindView(R.id.section_textView) TextView sectionTextView;
        @BindView(R.id.date_textView) TextView dateTextView;
        @BindView(R.id.author_textView) TextView authorTextView;

        public ViewHolder(View itemView, String webLink) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.webLink = webLink;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), webLink + " position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}

