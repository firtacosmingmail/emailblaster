package com.cosmin.emailblaster.ui.emailList;

import androidx.lifecycle.ViewModel;

import com.cosmin.emailblaster.data.EmailRepository;
import com.cosmin.emailblaster.data.LoginRepository;
import com.cosmin.emailblaster.data.model.UserContext;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EmailViewModel extends ViewModel {
    private final LoginRepository loginRepo;
    @Inject
    public EmailViewModel(LoginRepository loginRepo){
        this.loginRepo = loginRepo;
    }

    public void logout(){
        loginRepo.logout();
    }
}
