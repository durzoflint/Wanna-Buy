package com.nyxwolves.wannabuy.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.Activities.HomeActivity;
import com.nyxwolves.wannabuy.R;

public class MyHomeFragment extends Fragment {

    private static MyHomeFragment instance;
    private FragmentToActivity changeIconInterface;

    public MyHomeFragment(){}

    public static MyHomeFragment getInstance(){
        if(instance == null) {
            instance = new MyHomeFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeIconInterface = (HomeActivity) getActivity();
        changeIconInterface.changeIcon(getString(R.string.HOME_FRAGMENT));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}
