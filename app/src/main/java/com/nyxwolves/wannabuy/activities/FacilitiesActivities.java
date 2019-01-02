package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class FacilitiesActivities extends AppCompatActivity {

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_activities);

        nextBtn = findViewById(R.id.facilities_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacilitiesActivities.this,RoadWidth.class));
            }
        });
    }

    public void onRadioButtonClicked(View v){
        switch(v.getId()){
            case R.id.metro_yes:
                Requirements.getInstance().metroWater= getString(R.string.yes);
                break;
            case R.id.metro_no:
                Requirements.getInstance().metroWater= getString(R.string.no);
                break;
            case R.id.drainage_yes:
                Requirements.getInstance().drainageConnection = getString(R.string.yes);
                break;
            case R.id.drainage_no:
                Requirements.getInstance().drainageConnection = getString(R.string.no);
                break;
        }
    }
}