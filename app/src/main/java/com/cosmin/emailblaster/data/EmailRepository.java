package com.cosmin.emailblaster.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.cosmin.emailblaster.data.model.Email;
import com.cosmin.emailblaster.data.model.UserContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

public class EmailRepository {

    private final UserContext userContext;
    private final EmailDataSource dataSource;

    private Map<String,Email> emails = null;

    private final MediatorLiveData<Result<List<Email>>> mldEmails = new MediatorLiveData<>();
    public LiveData<Result<List<Email>>> ldEmails = mldEmails;

    public EmailRepository(EmailDataSource dataSource, UserContext userContext) {
        this.dataSource = dataSource;
        this.userContext = userContext;
        mldEmails.addSource(dataSource.LDInboxEmails, this::saveEmails);
    }

    private void saveEmails(Result result) {
        if ( result instanceof Result.Success ) {
            List<EmailMessage> emailList = ((Result.Success<List<EmailMessage>>) result).getData();
            emails = new HashMap<>();
            List<Email> returnList = new ArrayList();
            for ( EmailMessage email : emailList ) {
                try {
                    Email emailToSave = new Email((email));
                    emails.put(email.getId().getUniqueId(), emailToSave);
                    returnList.add(emailToSave);
                } catch (ServiceLocalException e) {
                    e.printStackTrace();
                }
            }
            mldEmails.postValue(new Result.Success<>(returnList));
        } else {
            mldEmails.postValue(result);
        }

    }

    public LiveData<Result<List<Email>>> fetchEmails(boolean refresh) {
        if ( emails == null || refresh) {
            dataSource.fetchInboxEmails(userContext.getExchangeService());
        }
        return ldEmails;
    }

    public Email getEmail(String uniqueID) {
        return emails.get(uniqueID);
    }

}
