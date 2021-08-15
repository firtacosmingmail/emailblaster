package com.cosmin.emailblaster.di;

import com.cosmin.emailblaster.data.EmailDataSource;
import com.cosmin.emailblaster.data.EmailRepository;
import com.cosmin.emailblaster.data.LoginRepository;
import com.cosmin.emailblaster.data.model.UserContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    LoginRepository provideLoginRepo(EmailDataSource emailDataSource, UserContext userContext) {
        return new LoginRepository(emailDataSource, userContext);
    }

    @Provides
    @Singleton
    EmailRepository provideEmailRepository(EmailDataSource emailDataSource, UserContext userContext) {
        return new EmailRepository(emailDataSource, userContext);
    }

    @Provides
    @Singleton
    EmailDataSource provideLoginDataSource() {
        return new EmailDataSource();
    }

    @Provides
    @Singleton
    UserContext provideUserContext(){
        return new UserContext();
    }
}
