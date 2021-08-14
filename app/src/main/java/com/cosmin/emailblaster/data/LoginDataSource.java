package com.cosmin.emailblaster.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cosmin.emailblaster.data.model.LoggedInUser;

import java.io.IOException;
import java.net.URI;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private MutableLiveData<Result<LoggedInUser>> mLDUser = new MutableLiveData<>();
    public LiveData<Result<LoggedInUser>> LDUser = mLDUser;

    public LiveData<Result<LoggedInUser>> login(String email, String password) {

        new Thread(() -> {
            executeLogin(email, password);
        }).start();

        return LDUser;
    }

    private void executeLogin(String email, String password) {

        try {
            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
            ExchangeCredentials credentials = new WebCredentials(email, password);
            service.setCredentials(credentials);
//            service.autodiscoverUrl(email);
            service.setUrl(new URI("https://www.outlook.com/EWS/exchange.asmx"));
            mLDUser.postValue(new Result.Success<LoggedInUser>(new LoggedInUser("UUID",email)));
        } catch (Exception e) {
            e.printStackTrace();
            mLDUser.postValue(new Result.Error(e));
        }
    }

    public void logout() {
    }
}