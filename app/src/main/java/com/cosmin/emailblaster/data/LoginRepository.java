package com.cosmin.emailblaster.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.cosmin.emailblaster.data.model.UserContext;

import microsoft.exchange.webservices.data.core.ExchangeService;

public class LoginRepository {

    private final UserContext userContext;
    private final EmailDataSource dataSource;

    private final MediatorLiveData<UserContext> mldLogin = new MediatorLiveData<>();
    public LiveData<UserContext> ldUser = mldLogin;

    public LoginRepository(EmailDataSource dataSource, UserContext userContext) {
        this.dataSource = dataSource;
        this.userContext = userContext;
    }

    public boolean isLoggedIn() {
        return userContext.getUser() != null;
    }

    public void logout() {
        userContext.clear();
        mldLogin.postValue(null);
    }

    public LiveData<UserContext> login(String email, String password) {
        mldLogin.addSource(dataSource.login(email, password), result -> {
            mldLogin.removeSource(dataSource.getUserLD());
            if ( result.getClass() == Result.Success.class){
                userContext.saveContext(email, password, ((Result.Success<ExchangeService>) result).getData());
                mldLogin.postValue(userContext);
            } else {
                mldLogin.postValue(null);
            }
        });
        return ldUser;
    }
}