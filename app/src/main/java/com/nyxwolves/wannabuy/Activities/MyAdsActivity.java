package com.nyxwolves.wannabuy.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.nyxwolves.wannabuy.Fragments.ViewPagerAdapter;
import com.nyxwolves.wannabuy.R;

public class MyAdsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    Toolbar toolBar;
    ViewPager viewPager;

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
    }
}
