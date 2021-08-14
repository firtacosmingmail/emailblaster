package com.cosmin.emailblaster.utils;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

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
}
