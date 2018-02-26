package com.example.android.newsappabnd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    public static final String LOG_DATA = MainActivity.class.getName();

    @BindView(R.id.main_RecyclerView) RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<Cat> cats = new ArrayList<>();
        for (int i = 0; i < 60; i++){
            cats.add(new Cat("cat" + String.valueOf(i)));
        }

        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, cats);
        mainRecyclerView.setAdapter(adapter);
        
    }

}

