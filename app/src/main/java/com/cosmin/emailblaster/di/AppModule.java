package com.cosmin.emailblaster.di;

import com.cosmin.emailblaster.data.LoginDataSource;
import com.cosmin.emailblaster.data.LoginRepository;
import com.cosmin.emailblaster.data.model.LoggedInUser;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    LoginRepository provideLoginRepo(LoginDataSource loginDataSource) {
        return new LoginRepository(loginDataSource);
    }

    @Provides
    LoginDataSource provideLoginDataSource() {
        return new LoginDataSource();
    }
}
