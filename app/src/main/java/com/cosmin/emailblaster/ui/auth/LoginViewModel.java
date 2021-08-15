package com.cosmin.emailblaster.ui.auth;

import android.util.Patterns;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.data.LoginRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MediatorLiveData<LoginResult> loginResult = new MediatorLiveData<>();
    private LoginRepository loginRepository;

    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    @Inject
    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;

        loginResult.addSource(loginRepository.ldUser, result -> {
            hideLoading();
            if ( result != null ) {
                loginResult.setValue(new LoginResult(new LoggedInUserView(result.getUser().getEmail())));
            } else {
                LoginFormState state = new LoginFormState();
                state.setGeneralError(R.string.error_logging_in);
                loginFormState.setValue(state);
            }
        });
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    void clear() {
        loginResult.removeSource(loginRepository.ldUser);
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login() {
        if ( haveFieldsErrors(email.get(), password.get()) ) {
            return;
        }
        printLoading();
        loginRepository.login(email.get(), password.get());
    }

    private void printLoading() {
        LoginFormState state = new LoginFormState(null, null);
        state.setLoading(true);
        loginFormState.postValue(state);
    }

    private void hideLoading(){
        LoginFormState state = new LoginFormState(null, null);
        state.setLoading(false);
        loginFormState.postValue(state);
    }

    public boolean haveFieldsErrors(String username, String password) {
        boolean haveErrors = true;
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
            haveErrors = false;
        }
        return haveErrors;
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}