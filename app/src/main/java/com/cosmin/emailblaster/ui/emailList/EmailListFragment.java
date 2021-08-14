package com.cosmin.emailblaster.ui.emailList;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.databinding.EmailListFragmentBinding;

public class EmailListFragment extends Fragment implements LifecycleObserver {

    private EmailListViewModel mViewModel;
    EmailListFragmentBinding binding;

    public static EmailListFragment newInstance() {
        return new EmailListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = EmailListFragmentBinding.inflate(getLayoutInflater());
        getViewLifecycleOwner().getLifecycle().addObserver(this);
        return binding.getRoot();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreated(){
        mViewModel = new ViewModelProvider(this).get(EmailListViewModel.class);
        binding.setVm(mViewModel);
        mViewModel.viewLD.observe(this, emailListView -> {
            binding.setView(emailListView);
            binding.executePendingBindings();
        });
        mViewModel.eventLD.observe(this, destination -> {
            NavHostFragment.findNavController(EmailListFragment.this)
                    .navigate(R.id.action_emailListFragment_to_FirstFragment);
        });
        mViewModel.fragmentCreated();
    }

}