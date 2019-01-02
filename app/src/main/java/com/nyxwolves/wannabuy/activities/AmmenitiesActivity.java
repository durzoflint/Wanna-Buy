package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        nextBtn = findViewById(R.id.ammenities_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Requirements.getInstance().subType.equals(getString(R.string.apartments))){
                    startActivity(new Intent(AmmenitiesActivity.this, MaintanceActivity.class));
                }else{
                    startActivity(new Intent(AmmenitiesActivity.this,ApprovalActivity.class));
                }
            }
        });
    }

    private String setData(CheckBox checkBox){
        if(checkBox.isChecked()){
            Log.d("AMMMENITIES","YES");
            return "YES";
        }else{
            Log.d("AMMMENITIES","NO");
            return "NO";
        }
    }
    public void oncheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.gym_check:
                Requirements.getInstance().gym = setData((CheckBox)v);
                break;
            case R.id.power_check:
                Requirements.getInstance().powerBackup = setData((CheckBox)v);
                break;
            case R.id.security_check:
                Requirements.getInstance().securityGuard = setData((CheckBox)v);
                break;
            case R.id.lift_check:
                Requirements.getInstance().lift = setData((CheckBox)v);
                break;
            case R.id.swimming_check:
                Requirements.getInstance().swimmingPool = setData((CheckBox)v);
                break;
            case R.id.cafetria_check:
                Requirements.getInstance().cafetria = setData((CheckBox)v);
                break;
            case R.id.garden_check:
                Requirements.getInstance().garden = setData((CheckBox)v);
                break;
            case R.id.water_check:
                Requirements.getInstance().water = setData((CheckBox)v);
                break;
            case R.id.play_area:
                Requirements.getInstance().playArea = setData((CheckBox)v);
                break;
        }
    }
}
