package com.cosmin.emailblaster.ui.emailList.list;

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

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
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
        if (getActivity() != null) {
            getActivity().setTitle(R.string.email_list_title);
        }
        return binding.getRoot();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreated(){
        mViewModel = new ViewModelProvider(this).get(EmailListViewModel.class);
        binding.setVm(mViewModel);
        mViewModel.getViewLD().observe(this, emailListView -> {

            if ( emailListView.emails != null && emailListView.emails.size() > 0) {
                EmailListAdapter adapter = new EmailListAdapter();
                adapter.setEmails(emailListView.emails);
                binding.setAdapter(adapter);
            }

            binding.setView(emailListView);
            binding.executePendingBindings();
        });
        mViewModel.getEventMLD().observe(this, destination -> {
            NavHostFragment.findNavController(EmailListFragment.this)
                    .navigate(R.id.action_emailListFragment_to_emailFragment);
        });
        mViewModel.fragmentCreated();
    }

}