package com.example.android.newsappabnd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetianakolesnik on 25/02/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public static final String LOG_TAG = RecyclerViewAdapter.class.getName();


    private LayoutInflater layoutInflater;
    private List<NewsStory> mData;

    public RecyclerViewAdapter(Context context, List<NewsStory> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_text_view) TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     This method is called right when the adapter is created and
     is used to initialize your ViewHolder(s).
     * @return new ViewHolder
     */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    /**
    This method is called for each ViewHolder to bind it to the adapter.
    This is where we will pass our data to our ViewHolder.
     */

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        NewsStory item = mData.get(position);
        holder.textView.setText(item.getNewsStory());
    }

    /**
     * This method returns the size of the collection that contains the items we want to display
     * @return number of items to be listed
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }


}

