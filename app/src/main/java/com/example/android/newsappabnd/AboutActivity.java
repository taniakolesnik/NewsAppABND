package com.example.android.newsappabnd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetianakolesnik on 11/03/2018.
 */

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.about_createdBy) TextView createdBy;
    @BindView(R.id.about_version) TextView version;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        ButterKnife.bind(this);
        createdBy.setText(R.string.about_createdBy);
        version.setText(R.string.about_version);
    }
}
