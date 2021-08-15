package com.cosmin.emailblaster.ui.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cosmin.emailblaster.data.LoginRepository;
import com.cosmin.emailblaster.data.Result;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SplashScreenViewModel extends ViewModel {

    private final MutableLiveData<SplashScreenDestination> destinationMutableLiveData = new MutableLiveData();

    private LoginRepository repo;

    @Inject
    public SplashScreenViewModel(LoginRepository repository) {
        repo = repository;
    }

    public void checkDestination(){
        if ( repo.isLoggedIn() ) {
            destinationMutableLiveData.postValue(SplashScreenDestination.EMAILS);
        } else {
            destinationMutableLiveData.postValue(SplashScreenDestination.LOGIN);
        }
    }

    public LiveData<SplashScreenDestination> getDestinationLD() {return destinationMutableLiveData; }


}
