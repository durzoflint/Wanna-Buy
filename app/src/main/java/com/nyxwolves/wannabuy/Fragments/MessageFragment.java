package com.nyxwolves.wannabuy.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyxwolves.wannabuy.Activities.HomeActivity;
import com.nyxwolves.wannabuy.R;


public class MessageFragment extends Fragment {

    private static MessageFragment instance;
    private FragmentToActivity changeIconInterface;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment getInstance(){
        if(instance == null){
            instance = new MessageFragment();
        }
        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeIconInterface = (HomeActivity) getActivity();
        changeIconInterface.changeIcon(getString(R.string.MSG_FRAGMENT));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }


}
