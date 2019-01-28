package com.nyxwolves.wannabuy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nyxwolves.wannabuy.Adapters.MyAdsAdapter;
import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;
import com.nyxwolves.wannabuy.activities.AdsActivity;
import com.nyxwolves.wannabuy.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdsFragment extends Fragment {

    RecyclerView myAdList;
    Button postReqBtn;
    SharedPreferences sharedPreferences;
    CardView firstAdCard;

    MyAdsAdapter adsAdapter;

    public MyAdsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ads, container, false);

        postReqBtn = view.findViewById(R.id.first_ad_btn);
        postReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdsActivity.class));
            }
        });

        firstAdCard = view.findViewById(R.id.first_time_ad_layout);
        firstAdCard.setVisibility(View.GONE);

        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(getString(R.string.shared_first_ad), true)) {
            firstAdCard.setVisibility(View.GONE);
        }

        //recyclerview
        myAdList = view.findViewById(R.id.my_ads_list);
        myAdList.setVisibility(View.VISIBLE);
        myAdList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adsAdapter = new MyAdsAdapter(getActivity());
        myAdList.setAdapter(adsAdapter);

        return view;
    }
}
