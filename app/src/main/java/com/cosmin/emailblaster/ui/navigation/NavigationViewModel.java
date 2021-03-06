package com.cosmin.emailblaster.ui.navigation;

import androidx.lifecycle.LiveData;

import com.cosmin.emailblaster.utils.SingleLiveEvent;

public class NavigationViewModel {

    NavigationData currentScreen;

    private final SingleLiveEvent<NavigationData> navigationLD = new SingleLiveEvent<>();
    public LiveData<NavigationData> getNavigationLD() { return navigationLD; }

    public void postNavigation(NavigationData navigationData) {
        currentScreen = navigationData;
        navigationLD.postValue(navigationData);
    }
}
