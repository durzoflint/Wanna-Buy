package com.nyxwolves.wannabuy.Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>fragments = new ArrayList<>();
    private ArrayList<String>titleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager){
        super(manager);
        MyAdsFragment myPostedAds = new MyAdsFragment();
        MyMatchesFragment myMatches = new MyMatchesFragment();
        MyRequirementsFragment myRequirements = new MyRequirementsFragment();

        fragments.add(myMatches);
        fragments.add(myPostedAds);
        fragments.add(myRequirements);
        titleList.add("My Matches");
        titleList.add("My Ads");
        titleList.add("My Requirements");
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
