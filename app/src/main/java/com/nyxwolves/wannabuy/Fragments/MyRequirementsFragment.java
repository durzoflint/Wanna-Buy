package com.nyxwolves.wannabuy.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nyxwolves.wannabuy.Adapters.RequirementSearchAdapter;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.activities.BuyOrRent;

public class MyRequirementsFragment extends Fragment {

    RecyclerView myRequirementsList;
    Button postReqBtn;
    CardView firstReqCard;


    RequirementSearchAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_requirement,container,false);

        postReqBtn = view.findViewById(R.id.first_req_btn);
        postReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BuyOrRent.class));
            }
        });
        firstReqCard = view.findViewById(R.id.first_time_req_layout);

        //recycler list
        myRequirementsList = view.findViewById(R.id.my_requirements_list);
        adapter = new RequirementSearchAdapter(getActivity(),firstReqCard);
        myRequirementsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRequirementsList.setAdapter(adapter);

        return view;
    }

}
