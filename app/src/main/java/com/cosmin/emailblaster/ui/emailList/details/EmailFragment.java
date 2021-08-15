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
import com.cosmin.emailblaster.data.Result;
import com.cosmin.emailblaster.data.model.Email;
import com.cosmin.emailblaster.databinding.EmailFragmentBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EmailFragment extends Fragment implements LifecycleObserver {

    private static final String EMAIL_ID_ARGUMENT_KEY = "EMAIL_ID_ARGUMENT_KEY";

    private EmailViewModel mViewModel;
    private EmailFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        getViewLifecycleOwner().getLifecycle().addObserver(this);
        binding = EmailFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreated() {
        mViewModel = new ViewModelProvider(this).get(EmailViewModel.class);
        mViewModel.getEmailLD().observe(this, result -> {
            if ( result instanceof Result.Success ) {
                binding.setEmail(((Result.Success<Email>) result).getData());
            }
        });
        mViewModel.fetchEmailByUniqueID(getArguments().getString(EMAIL_ID_ARGUMENT_KEY));
    }

}
