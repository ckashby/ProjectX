package com.meteoru.kalei.projectx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Repository {
    public String mName, mUrl;

    public Repository() { }

    public Repository(String name, String url) {
        this.mName = name;
        this.mUrl = url;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public static Repository fromJson(JSONObject jsonObject) {
        Repository repo = new Repository();
        try {
            repo.setmName(jsonObject.getString("teams_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repo;
    }

    public static ArrayList<Repository> fromJsonArray(JSONArray jsonArray) {
        ArrayList<Repository> repos = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject repoJson = null;
            try {
                repoJson = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            Repository repo = fromJson(repoJson);
            if (repo != null) {
                repos.add(repo);
            }
        }
        return repos;
    }
}
