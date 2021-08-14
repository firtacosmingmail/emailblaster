package com.cosmin.emailblaster.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.cosmin.emailblaster.data.model.LoggedInUser;

public class LoginRepository {

    private LoginDataSource dataSource;
    private MediatorLiveData<LoggedInUser> mldLogin = new MediatorLiveData<>();
    public LiveData<LoggedInUser> ldUser = mldLogin;

    private LoggedInUser user = null;

    public LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    public LiveData<LoggedInUser> login(String username, String password) {
        mldLogin.addSource(dataSource.login(username, password), result -> {
            mldLogin.removeSource(dataSource.LDUser);
            if ( result.getClass() == Result.Success.class){
                user = ((Result.Success<LoggedInUser>) result).getData();
                mldLogin.postValue(user);
            } else {
                mldLogin.postValue(null);
            }
        });
        return ldUser;
    }
}