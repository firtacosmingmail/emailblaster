package com.cosmin.emailblaster.utils;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.textfield.TextInputLayout;

public class BindingAdapters {


    @BindingAdapter("errorText")
    public static void setErrorMessage(TextInputLayout view, String errorMessage) {
        view.setError(errorMessage);
    }
    @BindingAdapter("errorStringID")
    public static void setErrorMessage(TextInputLayout view, Integer errorStringID) {
        if ( errorStringID!= null ) {
            view.setError(view.getContext().getText(errorStringID));
        }
    }

    @BindingAdapter("htmlText")
    public static void setHTMLTextStringId(AppCompatTextView view, String htmlText) {
        if ( htmlText != null ) {
            view.setText(HtmlCompat.fromHtml(htmlText, 0));
        }
    }

    @BindingAdapter("text")
    public static void setTextStringId(AppCompatTextView view, Integer stringID) {
        if ( stringID != null ) {
            view.setText(view.getContext().getText(stringID));
        }
    }

    @BindingAdapter("adapter")
    public static void setRecyclerViewAdapter(RecyclerView recView, BindableRecyclerViewAdapter adapter) {
        if ( adapter != null ) {
            adapter.bindRecyclerView(recView);
        }
    }


    @BindingAdapter("onRefresh")
    public static void onRefresh(SwipeRefreshLayout layout, SwipeRefreshLayout.OnRefreshListener listener){
        layout.setOnRefreshListener(listener);
    }

    @BindingAdapter("isRefreshing")
    public static void SwipeRefreshLayoutLoading(SwipeRefreshLayout layout, Boolean isRefreshing){
        layout.setRefreshing( isRefreshing );
    }
}
