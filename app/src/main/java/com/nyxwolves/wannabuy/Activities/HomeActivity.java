package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.Fragments.AdsFragment;
import com.nyxwolves.wannabuy.Fragments.FragmentToActivity;
import com.nyxwolves.wannabuy.Fragments.MessageFragment;
import com.nyxwolves.wannabuy.Fragments.MyHomeFragment;
import com.nyxwolves.wannabuy.Fragments.ProfileFragment;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;
import com.nyxwolves.wannabuy.RestApiHelper.RequirementHelper;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, FragmentToActivity, NavigationView.OnNavigationItemSelectedListener{


    ImageView homeButton,msgButton,adsButton,profileButton,postRequirement;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        if(getIntent().getAction() != null) {
            if (getIntent().getAction().equals(getString(R.string.POST_REQUIREMENT))) {
                RequirementHelper createHelper = new RequirementHelper(HomeActivity.this);
                createHelper.createRequirement();
            }
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homeButton = findViewById(R.id.nav_home_btn);
        homeButton.setOnClickListener(this);

        msgButton = findViewById(R.id.nav_msg_btn);
        msgButton.setOnClickListener(this);

        adsButton = findViewById(R.id.nav_ads_btn);
        adsButton.setOnClickListener(this);

        profileButton = findViewById(R.id.nav_account_btn);
        profileButton.setOnClickListener(this);

        postRequirement = findViewById(R.id.wanna_buy);
        postRequirement.setOnClickListener(this);

        fragmentManager= getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_holder,MyHomeFragment.getInstance()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                startActivity(new Intent(HomeActivity.this,RequirementsSearchActivity.class));
                break;
            case R.id.notify_icon:
                Toast.makeText(HomeActivity.this, "Notify", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.nav_home_btn:
                FragmentTransaction homeTransaction = fragmentManager.beginTransaction();
                homeTransaction.replace(R.id.fragment_holder,MyHomeFragment.getInstance()).commit();
                break;
            case R.id.nav_msg_btn:
                FragmentTransaction messageTransaction = fragmentManager.beginTransaction();
                messageTransaction.replace(R.id.fragment_holder, MessageFragment.getInstance()).commit();
                break;
            case R.id.nav_ads_btn:
                FragmentTransaction adsTransaction = fragmentManager.beginTransaction();
                adsTransaction.replace(R.id.fragment_holder, AdsFragment.getInstance()).commit();
                break;
            case R.id.nav_account_btn:
                FragmentTransaction accountTransaction = fragmentManager.beginTransaction();
                accountTransaction.replace(R.id.fragment_holder, ProfileFragment.getInstance()).commit();
                break;
            case R.id.wanna_buy:
                startActivity(new Intent(HomeActivity.this,BuyOrRent.class));
                break;
        }
    }

    @Override
    public void changeIcon(String option) {
        if(option.equals(getString(R.string.HOME_FRAGMENT))){
            homeButton.setImageDrawable(getDrawable(R.drawable.home_orange));
            adsButton.setImageDrawable(getDrawable(R.drawable.ads_blue));
            profileButton.setImageDrawable(getDrawable(R.drawable.acc_blue));
            msgButton.setImageDrawable(getDrawable(R.drawable.msg_blue));
        }
        else if(option.equals(getString(R.string.ADS_FRAGMENT))){
            adsButton.setImageDrawable(getDrawable(R.drawable.ads_orange));
            homeButton.setImageDrawable(getDrawable(R.drawable.home_blue));
            profileButton.setImageDrawable(getDrawable(R.drawable.acc_blue));
            msgButton.setImageDrawable(getDrawable(R.drawable.msg_blue));
        }
        else if(option.equals(getString(R.string.PROF_FRAGMENT))){
            profileButton.setImageDrawable(getDrawable(R.drawable.acc_orange));
            adsButton.setImageDrawable(getDrawable(R.drawable.ads_blue));
            homeButton.setImageDrawable(getDrawable(R.drawable.home_blue));
            msgButton.setImageDrawable(getDrawable(R.drawable.msg_blue));
        }
        else{
            msgButton.setImageDrawable(getDrawable(R.drawable.msg_orange));
            adsButton.setImageDrawable(getDrawable(R.drawable.ads_blue));
            homeButton.setImageDrawable(getDrawable(R.drawable.home_blue));
            profileButton.setImageDrawable(getDrawable(R.drawable.acc_blue));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this,LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
        }
        return false;
    }
}
