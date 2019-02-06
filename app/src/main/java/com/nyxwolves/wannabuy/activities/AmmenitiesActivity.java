package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class AmmenitiesActivity extends AppCompatActivity {

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ammenities);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nextBtn = findViewById(R.id.ammenities_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Requirements.getInstance().buyorRent.equals(getString(R.string.rent_text)) &&
                        Requirements.getInstance().subType.equals(getString(R.string.apartments))){
                        startActivity(new Intent(AmmenitiesActivity.this,PetsActivity.class));
                }else {
                    startActivity(new Intent(AmmenitiesActivity.this, ApprovalActivity.class));
                }
            }
        });
    }

    private String setData(CheckBox checkBox, String data){
        if(checkBox.isChecked()){
            Requirements.getInstance().facilitiesList.add(data);
            return getString(R.string.yes);
        }else{
            Requirements.getInstance().facilitiesList.remove(data);
            return getString(R.string.no);
        }
    }
    public void oncheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.gym_check:
                setData((CheckBox)v, getString(R.string.gym));
                break;
            case R.id.power_check:
                setData((CheckBox)v, getString(R.string.power_backup));
                break;
            case R.id.security_check:
                setData((CheckBox)v, getString(R.string.security_guard));
                break;
            case R.id.lift_check:
                setData((CheckBox)v, getString(R.string.lift));
                break;
            case R.id.swimming_check:
                setData((CheckBox)v, getString(R.string.swimming_pool));
                break;
            case R.id.cafetria_check:
                setData((CheckBox)v, getString(R.string.water));
                break;
            case R.id.garden_check:
                setData((CheckBox)v, getString(R.string.garden));
                break;
            case R.id.water_check:
                setData((CheckBox)v, getString(R.string.water));
                break;
            case R.id.play_area:
                setData((CheckBox)v, getString(R.string.play_area));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().facilitiesList.remove(getString(R.string.gym));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.security_guard));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.power_backup));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.play_area));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.water));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.swimming_pool));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.garden));
        Requirements.getInstance().facilitiesList.remove(getString(R.string.lift));
    }
}
