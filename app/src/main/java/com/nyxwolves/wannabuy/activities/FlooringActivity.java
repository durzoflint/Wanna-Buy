package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class FlooringActivity extends AppCompatActivity {

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flooring);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        nextBtn = findViewById(R.id.flooring_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FlooringActivity.this,FurnishedOrNot.class));
            }
        });
    }

    public void onCheckBoxClicked(View v){

    }
}



/*firstRow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //secondRow.clearCheck();
                //thirdRow.clearCheck();
                switch(checkedId){
                    case R.id.floor_one:
                        Requirements.getInstance().floor = "1";
                        break;
                    case R.id.floor_two:
                        Requirements.getInstance().floor = "2";
                        break;
                    case R.id.floor_three:
                        Requirements.getInstance().floor = "3";
                        break;
                    case R.id.floor_four:
                        Requirements.getInstance().floor = "4";
                        break;
                    case R.id.floor_five:
                        Requirements.getInstance().floor = "5";
                        break;
                    case R.id.floor_six:
                        Requirements.getInstance().floor = "6";
                        break;
                    case R.id.floor_seven:
                        Requirements.getInstance().floor = "7";
                        break;
                }
            }
        });

        secondRow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //firstRow.clearCheck();
                //thirdRow.clearCheck();
                switch(checkedId){
                    case R.id.floor_eight:
                        Requirements.getInstance().floor = "8";
                        break;
                    case R.id.floor_nine:
                        Requirements.getInstance().floor = "9";
                        break;
                    case R.id.floor_ten:
                        Requirements.getInstance().floor = "10";
                        break;
                    case R.id.floor_eleven:
                        Requirements.getInstance().floor = "11";
                        break;
                    case R.id.floor_12:
                        Requirements.getInstance().floor = "12";
                        break;
                    case R.id.floor_13:
                        Requirements.getInstance().floor = "13";
                        break;
                    case R.id.floor_14:
                        Requirements.getInstance().floor = "14";
                        break;
            }
            }
        });

        thirdRow.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //firstRow.clearCheck();
                //secondRow.clearCheck();
                switch(checkedId){
                    case R.id.floor_15:
                        Requirements.getInstance().floor = "15";
                        break;
                    case R.id.floor_16:
                        Requirements.getInstance().floor = "16";
                        break;
                    case R.id.floor_17:
                        Requirements.getInstance().floor = "17";
                        break;
                    case R.id.floor_18:
                        Requirements.getInstance().floor = "18";
                        break;
                    case R.id.floor_19:
                        Requirements.getInstance().floor = "19";
                        break;
                    case R.id.floor_20:
                        Requirements.getInstance().floor = "20";
                        break;
                    case R.id.floor_20_plus:
                        Requirements.getInstance().floor = "21";
                        break;
                }
            }
        });*/