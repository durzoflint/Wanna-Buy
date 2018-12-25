package com.nyxwolves.wannabuy.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nyxwolves.wannabuy.R;

public class FacilitiesActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_activities);
    }

    public void oncheckBoxClicked(View v){

        switch (v.getId()){
            case R.id.metro_check:
                break;
        }
    }
}