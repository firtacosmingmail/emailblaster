package com.cosmin.emailblaster.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.cosmin.emailblaster.data.model.UserContext;

import java.util.List;

import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

public class EmailRepository {

    private final UserContext userContext;
    private final EmailDataSource dataSource;

    private List<EmailMessage> emails = null;

    private final MediatorLiveData<Result<List<EmailMessage>>> mldEmails = new MediatorLiveData<>();
    public LiveData<Result<List<EmailMessage>>> ldEmails = mldEmails;

    public EmailRepository(EmailDataSource dataSource, UserContext userContext) {
        this.dataSource = dataSource;
        this.userContext = userContext;
        mldEmails.addSource(dataSource.LDInboxEmails, this::saveEmails);
    }

    private void saveEmails(Result<List<EmailMessage>> result) {
        if ( result instanceof Result.Success ) {
            this.emails = ((Result.Success<List<EmailMessage>>) result).getData();
            mldEmails.postValue(new Result.Success<>(emails));
        } else {
            mldEmails.postValue(result);
        }

    }

    public LiveData<Result<List<EmailMessage>>> fetchEmails(boolean refresh) {
        if ( emails == null || refresh) {
            dataSource.fetchInboxEmails(userContext.getExchangeService());
        }

        return ldEmails;
    }

    public void clear() {

    }
}
