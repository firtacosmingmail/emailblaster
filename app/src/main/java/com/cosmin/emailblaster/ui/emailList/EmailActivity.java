package com.cosmin.emailblaster.ui.emailList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.databinding.ActivityMainBinding;
import com.cosmin.emailblaster.ui.auth.AuthActivity;
import com.cosmin.emailblaster.ui.emailList.details.EmailFragment;
import com.cosmin.emailblaster.ui.navigation.NavigationViewModel;
import com.cosmin.emailblaster.ui.navigation.ScreenDestinations;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EmailActivity extends AppCompatActivity {

    @Inject
    public NavigationViewModel navVM;
    private ActivityMainBinding binding;

    @Inject
    public EmailPresenter presenter;

    public static Intent buildIntent(Context context) {
        return new Intent(context, EmailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        navVM.getNavigationLD().observe(this, navigationData -> {
            if ( navigationData.getDestination() == ScreenDestinations.EMAIL_DETAILS ) {
                showDetails(navigationData.getData());
            } else if ( navigationData.getDestination() == ScreenDestinations.EMAILS ) {
                showList();
            }
        });
    }

    void showDetails(Bundle dataForDetails) {
        changeDetailsVisibility(true);
        EmailFragment fragment = (EmailFragment) getSupportFragmentManager().
                findFragmentById(R.id.emailDetailsFragment);
        if ( fragment != null ) {
            fragment.update(dataForDetails);
        }
        presenter.detailsShown();
        setUpOnActionbar(true);
    }

    void showList() {
        changeDetailsVisibility(false);
        presenter.listShown();
        setUpOnActionbar(false);
    }

    void setUpOnActionbar(boolean isUpVisible){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            // if in landscape then the backbutton will not change.
            return;
        }
        ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ) {
            actionBar.setDisplayHomeAsUpEnabled(isUpVisible);
        }

    }

    void changeDetailsVisibility(boolean areDetailsVisible){
        EmailActivityView view = new EmailActivityView();
        view.setDisplayDetails(areDetailsVisible);
        binding.setView(view);
        binding.executePendingBindings();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            presenter.logout();
            startActivity(AuthActivity.buildIntent(this));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return presenter.onUpPressed();
    }

    @Override
    public void onBackPressed() {
        if (!presenter.onUpPressed()) {
            super.onBackPressed();
        }
    }
}