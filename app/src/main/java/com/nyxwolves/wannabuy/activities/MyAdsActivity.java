package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.nyxwolves.wannabuy.Fragments.ViewPagerAdapter;
import com.nyxwolves.wannabuy.Helpers.FirebaseHelper;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class MyAdsActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    TabLayout tabLayout;
    Toolbar toolBar;
    ViewPager viewPager;
    ImageView homeButton,profileButton,msgButton;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);

        toolBar = findViewById(R.id.my_ads_toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //drawer and navigation layout
        drawerLayout = findViewById(R.id.ads_drawer);
        navigationView = findViewById(R.id.ads_nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //change icon color
        ImageView addBtn = findViewById(R.id.nav_ads_btn);
        addBtn.setImageDrawable(getDrawable(R.drawable.ads_orange));

        ViewPagerAdapter fragmentAdapter = new ViewPagerAdapter(getSupportFragmentManager());


        viewPager = findViewById(R.id.ads_view_pager);
        tabLayout = findViewById(R.id.ads_tab_layout);
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

        homeButton = findViewById(R.id.nav_home_btn);
        homeButton.setOnClickListener(this);

        msgButton = findViewById(R.id.nav_msg_btn);
        msgButton.setOnClickListener(this);

        profileButton = findViewById(R.id.nav_account_btn);
        profileButton.setOnClickListener(this);

        ImageView wannaBuy = findViewById(R.id.wanna_buy);
        wannaBuy.setOnClickListener(this);

        //show fragment according to intent
        Intent intent = getIntent();
        if(intent.getAction() != null){
            if(intent.getAction().equals(getString(R.string.show_req))){
                viewPager.setCurrentItem(2,true);
            }else if(intent.getAction().equals(getString(R.string.show_match))){
                viewPager.setCurrentItem(0,true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_home_btn:
                super.onBackPressed();
                break;

            case R.id.nav_msg_btn:
                Intent i = new Intent(MyAdsActivity.this,MessageActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                break;

            case R.id.nav_account_btn:
                Intent i1 = new Intent(MyAdsActivity.this, AccountActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i1);
                break;
            case R.id.wanna_buy:
                startActivity(new Intent(MyAdsActivity.this, BuyOrRent.class));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.menu_my_requirements:
                Intent myReq = new Intent(MyAdsActivity.this,MyAdsActivity.class);
                myReq.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                myReq.setAction(getString(R.string.show_req));
                startActivity(myReq);
                break;

            case R.id.menu_my_matches:
                Intent myMatches = new Intent(MyAdsActivity.this,MyAdsActivity.class);
                myMatches.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                myMatches.setAction(getString(R.string.show_match));
                startActivity(myMatches);
                break;

            case R.id.menu_wanna_buy:
                Requirements.getInstance().buyorRent = getString(R.string.BUY);
                startActivity(new Intent(MyAdsActivity.this, StateInputActivity.class));
                break;

            case R.id.menu_wanna_rent:
                Requirements.getInstance().buyorRent = getString(R.string.RENT);
                startActivity(new Intent(MyAdsActivity.this, StateInputActivity.class));
                break;

            case R.id.menu_wanna_sell:
                startActivity(new Intent(MyAdsActivity.this,AdsActivity.class));
                break;

            case R.id.log_out:
                FirebaseHelper logoutHelper = new FirebaseHelper(this);
                logoutHelper.logOutUser();
                finish();
                break;

            case R.id.menu_about_us:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.about_us_url))));
                break;

            case R.id.menu_privacy_policy:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.privacy_policy_url))));
                break;

            case R.id.menu_terms_of_service:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.terms_of_service_url))));
                break;

            case R.id.menu_wanna_rent_out:
                startActivity(new Intent(MyAdsActivity.this,AdsActivity.class));
                break;

            case R.id.menu_how_we_work:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.how_we_work_url))));
                break;

            case R.id.payment_information:
                startActivity(new Intent(this, PaymentInformationActivity.class));
                break;

            case R.id.menu_contact_us:
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://wannabuy.in/contactus")));
                break;

        }
        return false;

    }
}
