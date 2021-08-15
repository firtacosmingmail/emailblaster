package com.cosmin.emailblaster.utils;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BindableRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    RecyclerView.LayoutManager layoutManager;

    protected abstract RecyclerView.LayoutManager createLayoutManager(Context context);

    void bindRecyclerView(RecyclerView recyclerView){
        layoutManager = createLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this);
    }
}
