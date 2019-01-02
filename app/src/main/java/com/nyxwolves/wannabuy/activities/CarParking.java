package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class CarParking extends AppCompatActivity {

    Button nextBtn;
    CheckBox checkBox;
    NumberPicker covPicker,unCovPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_parking);

        covPicker = findViewById(R.id.cov_picker);
        covPicker.setMaxValue(50);

        unCovPicker = findViewById(R.id.uncov_picker);
        unCovPicker.setMaxValue(50);

        nextBtn = findViewById(R.id.car_parking_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarParking.this,FacingActivity.class));
            }
        });
    }

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.covered_parking_check:
                checkBox = (CheckBox) v;
                if(checkBox.isChecked()) {
                    covPicker.setVisibility(View.VISIBLE);
                }else{
                    covPicker.setVisibility(View.GONE);
                }
                break;
            case R.id.un_cov_car_park:
                checkBox = (CheckBox) v;
                if(checkBox.isChecked()) {
                    unCovPicker.setVisibility(View.VISIBLE);
                }else{
                    unCovPicker.setVisibility(View.GONE);
                }
                break;
        }
    }
}
