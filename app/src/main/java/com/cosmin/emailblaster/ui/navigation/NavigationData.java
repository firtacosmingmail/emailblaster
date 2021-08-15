package com.cosmin.emailblaster.ui.navigation;

import android.os.Bundle;

public class NavigationData {

    ScreenDestinations destination;
    Bundle data;

    public NavigationData(ScreenDestinations destination, Bundle data) {
        setDestination(destination);
        setData(data);
    }

    public ScreenDestinations getDestination() {
        return destination;
    }

    public void setDestination(ScreenDestinations destination) {
        this.destination = destination;
    }

    public Bundle getData() {
        return data;
    }

    public void setData(Bundle data) {
        this.data = data;
    }
}
