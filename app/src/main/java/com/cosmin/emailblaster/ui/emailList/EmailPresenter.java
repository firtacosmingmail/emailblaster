package com.cosmin.emailblaster.ui.emailList;

import androidx.lifecycle.ViewModel;

import com.cosmin.emailblaster.data.LoginRepository;
import com.cosmin.emailblaster.ui.navigation.NavigationData;
import com.cosmin.emailblaster.ui.navigation.NavigationViewModel;
import com.cosmin.emailblaster.ui.navigation.ScreenDestinations;

import javax.inject.Inject;

/**
 * Using a presenter for the EmailActivity because it only responds to actions
 * and does no business data manipulation.
 */
public class EmailPresenter extends ViewModel {
    private final LoginRepository loginRepo;
    private final NavigationViewModel navViewModel;

    private boolean areDetailsShown = false;

    @Inject
    public EmailPresenter(LoginRepository loginRepo, NavigationViewModel navigationViewModel){
        this.loginRepo = loginRepo;
        this.navViewModel = navigationViewModel;
    }

    public void logout(){
        loginRepo.logout();
    }

    /**
     *
     * @return true if the details are displayed and the activity should not be finished.
     */
    public boolean onUpPressed() {
        if ( areDetailsShown ) {
            navViewModel.postNavigation(new NavigationData(ScreenDestinations.EMAILS, null));
            return true;
        } else {
            return false;
        }
    }

    public void listShown() {
        areDetailsShown = false;
    }
    public void detailsShown() {
        areDetailsShown = true;
    }
}
