package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void oncheckBoxClicked(View v){

        switch (v.getId()){
            case R.id.metro_check:
                break;
        }
    }
}