package com.nyxwolves.wannabuy.Fragments;

import android.content.Context;
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

import com.nyxwolves.wannabuy.Adapters.MatchesAdapter;
import com.nyxwolves.wannabuy.R;

public class MyMatchesFragment extends Fragment {

    RecyclerView matchesList;
    CardView noMatchesFound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_matches,container,false);

        noMatchesFound = view.findViewById(R.id.no_matches_card);
        //recycler view
        matchesList = view.findViewById(R.id.matches_list);
        MatchesAdapter matchesAdapter = new MatchesAdapter(getActivity(),noMatchesFound);
        matchesList.setAdapter(matchesAdapter);
        matchesList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


}
