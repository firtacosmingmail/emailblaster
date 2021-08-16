package com.cosmin.emailblaster.utils;

import android.text.Html;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.widget.AppCompatTextView;
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
        if (errorStringID != null) {
            view.setError(view.getContext().getText(errorStringID));
        }
    }

    @BindingAdapter("htmlText")
    public static void setHTMLTextStringId(AppCompatTextView view, String htmlText) {
        if (htmlText != null) {
            view.setText(Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT));
        }
    }

    @BindingAdapter("htmlText")
    public static void setHTMLTextToWebView(WebView view, String htmlText) {
        if (htmlText != null) {
            view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
            view.getSettings().setUseWideViewPort(false);
            String mimeType = "text/html";
            String encoding = "utf-8";
            // Load html source code into webview to show the html content.
            view.loadDataWithBaseURL(null, htmlText, mimeType, encoding, null);

        }
    }

    @BindingAdapter("text")
    public static void setTextStringId(AppCompatTextView view, Integer stringID) {
        if (stringID != null) {
            view.setText(view.getContext().getText(stringID));
        }
    }

    @BindingAdapter("adapter")
    public static void setRecyclerViewAdapter(RecyclerView recView, BindableRecyclerViewAdapter adapter) {
        if (adapter != null) {
            adapter.bindRecyclerView(recView);
        }
    }


    @BindingAdapter("onRefresh")
    public static void onRefresh(SwipeRefreshLayout layout, SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
    }

    @BindingAdapter("isRefreshing")
    public static void SwipeRefreshLayoutLoading(SwipeRefreshLayout layout, Boolean isRefreshing) {
        layout.setRefreshing(isRefreshing);
    }
}
