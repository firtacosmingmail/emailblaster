package com.cosmin.emailblaster.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cosmin.emailblaster.data.model.Email;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class EmailDataSource {
    private MutableLiveData<Result<ExchangeService>> mLDUser = new MutableLiveData<>();

    private MutableLiveData<Result<List<EmailMessage>>> mLDInboxEmails = new MutableLiveData<>();

    public LiveData<Result<ExchangeService>> login(String email, String password) {

        new Thread(() -> {
            executeLogin(email, password );
        }).start();

        return mLDUser;
    }

    private void executeLogin(String email, String password) {
        try {
            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
            ExchangeCredentials credentials = new WebCredentials(email, password);
            service.setCredentials(credentials);
            service.autodiscoverUrl(email);
//            service.setUrl(new URI("https://www.outlook.com/EWS/exchange.asmx"));
//            service.setUrl( new URI("https://outlook.office365.com/ews/exchange.asmx"));
            mLDUser.postValue(new Result.Success<>(service));
        } catch (Exception e) {
            e.printStackTrace();
            mLDUser.postValue(new Result.Error(e));
        }
    }

    public LiveData<Result<List<EmailMessage>>> fetchInboxEmails(ExchangeService service){
        new Thread(() -> {
            executeFetchEmails( service );
        }).start();

        return mLDInboxEmails;
    }

    private void executeFetchEmails(ExchangeService service) {
        try {
            Folder inbox = Folder.bind(service, WellKnownFolderName.Inbox);
            ArrayList<Item> items = inbox.findItems(new ItemView(Integer.MAX_VALUE)).getItems();
            ArrayList<EmailMessage> emails = new ArrayList<>();
            for ( Item item : items) {
                if ( item instanceof EmailMessage ) {
                    PropertySet propSet = new PropertySet(BasePropertySet.FirstClassProperties);
                    propSet.add(ItemSchema.Body);
                    propSet.add(ItemSchema.Subject);
                    propSet.add(ItemSchema.MimeContent);
                    propSet.add(ItemSchema.Id);
                    propSet.add(ItemSchema.DisplayTo);
                    EmailMessage em = EmailMessage.bind(service, item.getId(), propSet);
                    emails.add(em);
                }
            }
            mLDInboxEmails.postValue(new Result.Success<>(emails));
        } catch (Exception e) {
            mLDInboxEmails.postValue(new Result.Error(e));
        }
    }

    public LiveData<Result<List<EmailMessage>>> getInboxEmailLD(){ return mLDInboxEmails; }
    public LiveData<Result<ExchangeService>> getUserLD() { return mLDUser; }
}