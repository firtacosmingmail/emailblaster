package com.cosmin.emailblaster.ui.emailList.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cosmin.emailblaster.data.EmailRepository;
import com.cosmin.emailblaster.data.Result;
import com.cosmin.emailblaster.data.model.Email;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EmailViewModel extends ViewModel {
    private final EmailRepository emailRepo;

    MutableLiveData<Result<Email>> mldEmail = new MutableLiveData();

    @Inject
    public EmailViewModel(EmailRepository repo) {
        this.emailRepo = repo;
    }

    public LiveData<Result<Email>> getEmailLD() { return mldEmail; }

    public void fetchEmailByUniqueID(String uniqueID){
        Email email = emailRepo.getEmail(uniqueID);
        mldEmail.postValue(new Result.Success<>(email));
    }

}