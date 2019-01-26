package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class FacilitiesActivities extends AppCompatActivity {

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities_activities);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nextBtn = findViewById(R.id.facilities_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FacilitiesActivities.this,RoadWidth.class));
            }
        });
    }

    private void setData(CheckBox v,  String data){
        if(v.isChecked()){
            Requirements.getInstance().facilitiesList.add(data);
        }else{
            Requirements.getInstance().facilitiesList.remove(data);
        }
    }

    public void onCheckBoxClicked(View v){
        switch(v.getId()){
            case R.id.ground_water_check:
                setData((CheckBox)v, getString(R.string.ground_water));
                break;
            case R.id.corp_water_check:
                setData((CheckBox)v, getString(R.string.corp_water));
                break;
            case R.id.drainage_check:
                setData((CheckBox)v, getString(R.string.drainage_connection));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().facilitiesList.remove(getString(R.string.drainage_connection));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.corp_water));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.ground_water));
    }
}