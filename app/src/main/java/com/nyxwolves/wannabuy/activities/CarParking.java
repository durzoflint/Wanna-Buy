package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class CarParking extends AppCompatActivity {

    Button nextBtn;
    CheckBox checkBox;
    NumberPicker covPicker,unCovPicker;
    LinearLayout covLayout,unCovLayout;

    int noOfCov = -1;
    int noOfUnCov = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_parking);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        covLayout =  findViewById(R.id.cov_park_layout);
        unCovLayout = findViewById(R.id.un_cov_park_layout);

        covPicker = findViewById(R.id.cov_picker);
        covPicker.setMaxValue(5);
        covPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                noOfCov = newVal;
            }
        });

        unCovPicker = findViewById(R.id.uncov_picker);
        unCovPicker.setMaxValue(5);
        unCovPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                noOfUnCov = newVal;
            }
        });

        nextBtn = findViewById(R.id.car_parking_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Requirements.getInstance().noOfUnCovParking = String.valueOf(noOfUnCov);
                Requirements.getInstance().noOfCovParking = String.valueOf(noOfCov);
                if(Requirements.getInstance().checkCovParking() || Requirements.getInstance().checkUnCovParking()){
                    startActivity(new Intent(CarParking.this,FacingActivity.class));
                }

            }
        });
    }

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.covered_parking_check:
                checkBox = (CheckBox) v;
                if(checkBox.isChecked()) {
                    Requirements.getInstance().isCovparking = getString(R.string.yes);
                    covLayout.setVisibility(View.VISIBLE);
                }else{
                    Requirements.getInstance().isCovparking = getString(R.string.no);
                    covLayout.setVisibility(View.GONE);
                }
                break;

            case R.id.un_cov_car_park:
                checkBox = (CheckBox) v;
                if(checkBox.isChecked()) {
                    Requirements.getInstance().isUnCovParking = getString(R.string.yes);
                    unCovLayout.setVisibility(View.VISIBLE);
                }else{
                    Requirements.getInstance().isUnCovParking = getString(R.string.no);
                    unCovLayout.setVisibility(View.GONE);
                }
                break;
        }
    }
}
