package com.cosmin.emailblaster.ui.emailList.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.data.EmailRepository;
import com.cosmin.emailblaster.data.Result;
import com.cosmin.emailblaster.data.model.Email;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

@HiltViewModel
public class EmailListViewModel extends ViewModel {

    private final EmailRepository repo;
    private final MutableLiveData<String> eventMLD = new MutableLiveData<>();
    private final MediatorLiveData<EmailListView> viewMLD = new MediatorLiveData<>();
    private List<Email> emailList;

    @Inject
    public EmailListViewModel(EmailRepository repo) {
        this.repo = repo;
        viewMLD.addSource(repo.ldEmails, this::emailsReceived);
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
        this.repo.fetchEmails(true);
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

    public LiveData<String> getEventMLD() { return eventMLD; }
    public LiveData<EmailListView> getViewLD() { return viewMLD; }
}