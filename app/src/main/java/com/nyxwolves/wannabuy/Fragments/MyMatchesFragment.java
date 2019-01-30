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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.Adapters.MatchesAdapter;
import com.nyxwolves.wannabuy.R;

public class MyMatchesFragment extends Fragment {

    RecyclerView matchesList;
    CardView noMatchesFound;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_matches,container,false);

        //recycler view
        matchesList = view.findViewById(R.id.matches_list);
        MatchesAdapter matchesAdapter = new MatchesAdapter(getActivity());
        matchesList.setAdapter(matchesAdapter);
        matchesList.setLayoutManager(new LinearLayoutManager(getActivity()));

        noMatchesFound = view.findViewById(R.id.no_matches_card);

        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(getString(R.string.shared_first_match), true)) {
            noMatchesFound.setVisibility(View.VISIBLE);
        }
        return view;
    }
}
