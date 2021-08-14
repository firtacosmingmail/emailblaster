package com.cosmin.emailblaster.ui.emailList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EmailListViewModel extends ViewModel {

    private EmailListView view = new EmailListView();

    private MutableLiveData<String> eventMLD = new MutableLiveData<>();
    public LiveData<String> eventLD = eventMLD;

    private MutableLiveData<EmailListView> viewMLD = new MutableLiveData<>();
    public LiveData<EmailListView> viewLD = viewMLD;

    public void buttonClicked() {
        eventMLD.postValue("go back");
    }

    public void fragmentCreated() {
        viewMLD.postValue(view);
    }
}