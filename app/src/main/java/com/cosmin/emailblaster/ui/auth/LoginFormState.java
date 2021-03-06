package com.cosmin.emailblaster.ui.auth;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
public class LoginFormState {
    @Nullable
    public Integer emailError;
    @Nullable
    public Integer passwordError;
    public boolean isDataValid;
    private boolean loading;
    private Integer generalError;

    LoginFormState(@Nullable Integer emailError, @Nullable Integer passwordError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.emailError = null;
        this.passwordError = null;
        this.isDataValid = isDataValid;
    }
    LoginFormState() {}

    @Nullable
    Integer getEmailError() {
        return emailError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public Integer getGeneralError() {
        return generalError;
    }

    public void setGeneralError(Integer generalError) {
        this.generalError = generalError;
    }
}