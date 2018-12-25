package com.nyxwolves.wannabuy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nyxwolves.wannabuy.activities.AdsActivity;
import com.nyxwolves.wannabuy.R;

public class MyAdsFragment extends Fragment {

    RecyclerView myAdList;
    Button postReqBtn;
    SharedPreferences sharedPreferences;
    CardView firstAdCard;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_ads,container,false);

        myAdList = v.findViewById(R.id.my_ads_list);
        postReqBtn = v.findViewById(R.id.first_ad_btn);
        postReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdsActivity.class));
            }
        });

        firstAdCard = v.findViewById(R.id.first_time_ad_layout);
        firstAdCard.setVisibility(View.GONE);

        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("FIRST_REQ",true)){
            firstAdCard.setVisibility(View.VISIBLE);
        }

        return v;
    }
}
