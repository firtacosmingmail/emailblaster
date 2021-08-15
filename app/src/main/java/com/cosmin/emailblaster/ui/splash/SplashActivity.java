package com.cosmin.emailblaster.ui.splash;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.cosmin.emailblaster.databinding.ActivitySplashBinding;
import com.cosmin.emailblaster.ui.auth.AuthActivity;
import com.cosmin.emailblaster.ui.emailList.EmailActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private SplashScreenViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(null);
        vm = new ViewModelProvider(this).get(SplashScreenViewModel.class);
        vm.destinationLiveData.observe(this, destination -> {
            switch (destination) {
                case LOGIN: openAuthActivity();
                break;
                case EMAILS: openEmailsActivity();
                break;
            }
        });
        vm.checkDestination();
    }

    private void openAuthActivity() {
        startActivity(AuthActivity.buildIntent(this));
        finish();
    }

    private void openEmailsActivity() {
        startActivity(EmailActivity.buildIntent(this));
        finish();
    }
}