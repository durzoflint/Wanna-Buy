package com.nyxwolves.wannabuy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.nyxwolves.wannabuy.Adapters.MyRequirementsAdapter;
import com.nyxwolves.wannabuy.RestApiHelper.RequirementHelper;
import com.nyxwolves.wannabuy.activities.AdsActivity;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.activities.BuyOrRent;

public class MyRequirementsFragment extends Fragment {

    RecyclerView myRequirementsList;
    Button postReqBtn;
    CardView firstReqCard;
    SharedPreferences sharedPreferences;

    MyRequirementsAdapter adapter;
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
        firstReqCard.setVisibility(View.GONE);

        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("FIRST_REQ",true)){
            firstReqCard.setVisibility(View.GONE);
        }

        myRequirementsList = view.findViewById(R.id.my_requirements_list);
        RequirementHelper helper = new RequirementHelper(getActivity());
        //helper.getRequirement(getString(R.string.USER_ID_QUERY), FirebaseAuth.getInstance().getCurrentUser().getEmail().toUpperCase());
        adapter = new MyRequirementsAdapter();
        myRequirementsList.setLayoutManager(new GridLayoutManager(getActivity(),2));
        myRequirementsList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 10;
                outRect.top = 10;
                outRect.left = 10;
                outRect.right = 10;
            }
        });
        myRequirementsList.setAdapter(adapter);
        adapter.setData(helper.reqDataList);


        return view;
    }

}
