package com.cosmin.emailblaster.ui.emailList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cosmin.emailblaster.R;
import com.cosmin.emailblaster.ui.auth.AuthActivity;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.cosmin.emailblaster.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EmailActivity extends AppCompatActivity {

    public static Intent buildIntent(Context context) {
        return new Intent(context, EmailActivity.class);
    }

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private EmailViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        vm = new ViewModelProvider(this).get(EmailViewModel.class);
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
            vm.logout();
            startActivity(AuthActivity.buildIntent(this));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}