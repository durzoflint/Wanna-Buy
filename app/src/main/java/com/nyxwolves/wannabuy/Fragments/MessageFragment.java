package com.nyxwolves.wannabuy.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nyxwolves.wannabuy.Activities.HomeActivity;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.chat.ChatActivity;


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
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);
        LinearLayout abhinav = rootView.findViewById(R.id.abhinav);
        abhinav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });
        return rootView;
    }


}
