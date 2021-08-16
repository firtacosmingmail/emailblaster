package com.cosmin.emailblaster.ui.emailList.list;

import static com.cosmin.emailblaster.ui.navigation.ScreenDestinations.EMAIL_DETAILS;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.data.EmailRepository;
import com.cosmin.emailblaster.data.Result;
import com.cosmin.emailblaster.data.model.Email;
import com.cosmin.emailblaster.ui.navigation.NavigationData;
import com.cosmin.emailblaster.ui.navigation.NavigationViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EmailListViewModel extends ViewModel implements EmailSelectedListener {

    public final static String EMAIL_ID_ARGUMENT_KEY = "EMAIL_ID_ARGUMENT_KEY";

    private final EmailRepository repo;
    private final NavigationViewModel navVM;
    private final MediatorLiveData<EmailListView> viewMLD = new MediatorLiveData<>();
    private List<Email> emailList;

    @Inject
    public EmailListViewModel(EmailRepository repo, NavigationViewModel navigationViewModel) {
        this.repo = repo;
        this.navVM = navigationViewModel;
        viewMLD.addSource(repo.getMailLiveData(), this::emailsReceived);
    }

    public void emailsReceived(Result<List<Email>> result) {
        if (result instanceof Result.Success) {
            addEmailsToList(((Result.Success<List<Email>>) result).getData());
        } else {
            sendError();
        }
    }

    private void sendError() {
        EmailListView view = new EmailListView();
        view.errorMessage = R.string.error_getting_emails;
        viewMLD.postValue(view);
    }

    private void addEmailsToList(List<Email> data) {
        emailList = new ArrayList<>();
        emailList.addAll(data);
        EmailListView view = new EmailListView();
        view.emails = emailList;
        viewMLD.postValue(view);
    }

    public void fragmentCreated() {
        this.repo.fetchEmails(false);
        showLoading();
    }

    public void refreshEmail() {
        this.repo.fetchEmails(true);
        showLoading();
    }

    private void showLoading() {
        EmailListView view = new EmailListView();
        view.loading = true;
        viewMLD.postValue(view);
    }

    public LiveData<EmailListView> getViewLD() { return viewMLD; }

    @Override
    public void onEmailSelected(Email selectedEmail) {
        Bundle data = new Bundle();
        data.putString(EMAIL_ID_ARGUMENT_KEY, selectedEmail.getUniqueID());
        navVM.postNavigation(new NavigationData(EMAIL_DETAILS, data));
    }
}