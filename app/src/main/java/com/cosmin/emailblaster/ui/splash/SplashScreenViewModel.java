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

    private MutableLiveData<SplashScreenDestination> destinationMutableLiveData = new MutableLiveData();
    public LiveData<SplashScreenDestination> destinationLiveData = destinationMutableLiveData;

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


}
