package com.meteoru.kalei.projectx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    public static String BASE_URL = "https://api.github.com/users/ckashby/repos";
    ArrayList<Repository> repositoryArrayList;
    RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        repositoryArrayList = new ArrayList<Repository>();

        RecyclerView rvMain = (RecyclerView) findViewById(R.id.rvMain);
        AsyncHttpClient client = new AsyncHttpClient();
        client.setUserAgent("ckashby");
        RequestParams params = new RequestParams();
        client.get(BASE_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseArray) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                try {
                    repositoryArrayList.addAll(Repository.fromJsonArray(responseArray));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RepositoryAdapter(repositoryArrayList);
        rvMain.setAdapter(adapter);

    }
}
