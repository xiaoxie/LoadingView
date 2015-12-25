package com.jlb.mobile.loadingview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jlb.mobile.loadingview.library.LoadingLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(view -> Snackbar.make(view, "Clear logs", Snackbar.LENGTH_LONG)
                .setAction("Action", (v) -> {
                    Log.d("Action","Action") ;
                }).show());

        final LoadingLayout loadingLayout = (LoadingLayout) findViewById(R.id.loadingview);
        findViewById(R.id.btn_content).setOnClickListener((view) -> loadingLayout.showContent());
        findViewById(R.id.btn_error).setOnClickListener((view) -> loadingLayout.showError());
        findViewById(R.id.btn_empty).setOnClickListener((view) -> loadingLayout.showEmpty());
        findViewById(R.id.btn_loading).setOnClickListener((view) -> loadingLayout.showLoading());

        loadingLayout.setOnRetryClickListener((view) -> loadingLayout.showLoading());
        loadingLayout.setOnEmptyClickListener((view) -> loadingLayout.showLoading());
    }
}