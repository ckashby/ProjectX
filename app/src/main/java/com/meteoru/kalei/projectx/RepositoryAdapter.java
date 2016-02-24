package com.meteoru.kalei.projectx;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
    ArrayList<Repository> mRepositories;
    public RepositoryAdapter(ArrayList<Repository> repositories) {
        mRepositories = repositories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View customView = inf.inflate(R.layout.repository_item, parent, false);
        ViewHolder holder = new ViewHolder(customView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repository repo = mRepositories.get(position);
        TextView tvName = holder.tvName;
        tvName.setText(repo.mName);
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }
}
