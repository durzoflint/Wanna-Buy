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

public class MyRequirementsFragment extends Fragment {

    RecyclerView myRequirementsList;
    Button postReqBtn;
    CardView firstReqCard;

    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_my_requirement,container,false);



        postReqBtn = v.findViewById(R.id.first_req_btn);
        postReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdsActivity.class));
            }
        });
        firstReqCard = v.findViewById(R.id.first_time_req_layout);
        firstReqCard.setVisibility(View.GONE);

        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("FIRST_REQ",true)){
            firstReqCard.setVisibility(View.VISIBLE);
        }

        myRequirementsList = v.findViewById(R.id.my_requirements_list);

        return v;
    }
}
