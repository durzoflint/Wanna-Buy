package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.nyxwolves.wannabuy.Fragments.ViewPagerAdapter;
import com.nyxwolves.wannabuy.R;

public class MyAdsActivity extends AppCompatActivity implements View.OnClickListener{

    TabLayout tabLayout;
    Toolbar toolBar;
    ViewPager viewPager;
    ImageView homeButton,profileButton,msgButton;
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
}
