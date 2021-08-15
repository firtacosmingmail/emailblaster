package com.cosmin.emailblaster.ui.emailList.details;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.data.model.Email;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EmailFragment extends Fragment implements LifecycleObserver {

    private EmailViewModel mViewModel;

    public static EmailFragment newInstance(Email email) {

        return new EmailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        getViewLifecycleOwner().getLifecycle().addObserver(this);
        return inflater.inflate(R.layout.email_fragment, container, false);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreated(){
        mViewModel = new ViewModelProvider(this).get(EmailViewModel.class);
    }

}