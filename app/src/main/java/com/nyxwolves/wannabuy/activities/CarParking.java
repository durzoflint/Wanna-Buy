package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.nyxwolves.wannabuy.R;

public class CarParking extends AppCompatActivity {

    Button nextBtn;
    EditText noOfCoveredParking,noOfUnCoveredParking;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_parking);

        noOfUnCoveredParking = findViewById(R.id.no_of_un_cov_car_park);
        noOfCoveredParking = findViewById(R.id.no_of_cov_car_park);

        nextBtn = findViewById(R.id.car_parking_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarParking.this,AgeOfProperty.class));
            }
        });
    }

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.covered_parking_check:
                checkBox = (CheckBox) v;
                if(checkBox.isChecked()) {
                    noOfCoveredParking.setVisibility(View.VISIBLE);
                }else{
                    noOfCoveredParking.setVisibility(View.GONE);
                }
                break;
            case R.id.un_cov_car_park:
                checkBox = (CheckBox) v;
                if(checkBox.isChecked()) {
                    noOfUnCoveredParking.setVisibility(View.VISIBLE);
                }else{
                    noOfUnCoveredParking.setVisibility(View.GONE);
                }
                break;
        }
    }
}
