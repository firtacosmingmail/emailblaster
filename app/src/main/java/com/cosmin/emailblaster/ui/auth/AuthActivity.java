package com.cosmin.emailblaster.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.databinding.ActivityAuthBinding;
import com.cosmin.emailblaster.ui.emailList.EmailActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityAuthBinding binding;

    public static Intent buildIntent(Context context){
        return new Intent(context, AuthActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setVm(loginViewModel);

        loginViewModel.getLoginFormState().observe(this, loginFormState ->  {
            binding.setView(loginFormState);
        });

        loginViewModel.getLoginResult().observe(this, loginResult -> {
                if (loginResult == null) {
                    return;
                }
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                    startActivity(EmailActivity.buildIntent(getApplicationContext()));
                    finish();
                }
        });
    }

    public void onDestroy(){
        super.onDestroy();
        loginViewModel.clear();
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}