package com.cosmin.emailblaster.ui.emailList.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.data.EmailRepository;
import com.cosmin.emailblaster.data.Result;
import com.cosmin.emailblaster.data.model.Email;
import com.cosmin.emailblaster.data.model.EmailSender;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;

@HiltViewModel
public class EmailListViewModel extends ViewModel {

    private final EmailRepository repo;
    private List<Email> emailList;

    private MutableLiveData<String> eventMLD = new MutableLiveData<>();
    public LiveData<String> eventLD = eventMLD;

    private MediatorLiveData<EmailListView> viewMLD = new MediatorLiveData<>();
    public LiveData<EmailListView> viewLD = viewMLD;

    @Inject
    public EmailListViewModel(EmailRepository repo){
        this.repo = repo;
        viewMLD.addSource(repo.ldEmails, this::emailsReceived);
    }

    public void emailsReceived(Result<List<EmailMessage>> result){
        if ( result instanceof Result.Success ) {
            addEmailsToList(((Result.Success<List<EmailMessage>>) result).getData());
        } else {
            sendError();
        }
    }

    private void sendError() {
        EmailListView view = new EmailListView();
        view.errorMessage = R.string.error_getting_emails;
        viewMLD.postValue(view);
    }

    private void addEmailsToList(List<EmailMessage> data) {
        emailList = new ArrayList<>();
        for ( EmailMessage emailMessage : data ) {
            try {
                emailList.add(
                        new Email(
                            new EmailSender(
                                    emailMessage.getSender().getName(),
                                    emailMessage.getSender().getAddress()
                            ),
                            emailMessage.getBody().toString(),
                            emailMessage.getSubject()
                        )
                    );
            } catch (ServiceLocalException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        EmailListView view = new EmailListView();
        view.emails = emailList;
        viewMLD.postValue(view);
    }

    public void fragmentCreated() {
        this.repo.fetchEmails(true);
        showLoading();
    }

    public void refreshEmail(){
        this.repo.fetchEmails(true);
        showLoading();
    }

    private void showLoading() {
        EmailListView view = new EmailListView();
        view.loading = true;
        viewMLD.postValue(view);
    }
}