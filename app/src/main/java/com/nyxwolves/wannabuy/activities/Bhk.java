package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class Bhk extends AppCompatActivity {

    Button nextBtn;
    TextView modeHeader;
    EditText restOne, restTwo, restThree, restFour, restFive;

    boolean oneSelected = false;
    boolean twoSelected = false;
    boolean threeSelected = false;
    boolean fourSelected = false;
    boolean fiveSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhk);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        modeHeader = findViewById(R.id.bhk_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        restOne = findViewById(R.id.min_rest_room_one);
        restTwo = findViewById(R.id.min_rest_room_two);
        restThree = findViewById(R.id.min_rest_room_three);
        restFour = findViewById(R.id.min_rest_room_four);
        restFive = findViewById(R.id.min_rest_room_five);

        nextBtn = findViewById(R.id.bhk_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(oneSelected || twoSelected || threeSelected || fourSelected || fiveSelected){

                if (Requirements.getInstance().subType.equals(getString(R.string.residential_independent)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.pg_rent_independent))) {
                    startActivity(new Intent(Bhk.this, CarParking.class));

                } else if (Requirements.getInstance().subType.equals(getString(R.string.apartments)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.pg_rent_apartment)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace))) {
                    startActivity(new Intent(Bhk.this, FlooringActivity.class));

                } else {
                    startActivity(new Intent(Bhk.this, FacingActivity.class));

                }

                //}else{
                //Toast.makeText(Bhk.this,"Choose number of rooms",Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }

    private boolean setData(CheckBox checkBox, String data, EditText editText) {
        if (checkBox.isChecked()) {
            Requirements.getInstance().bhkList.add(data);
            editText.setVisibility(View.VISIBLE);
            return true;
        } else {
            Requirements.getInstance().bhkList.remove(data);
            editText.setVisibility(View.GONE);
            return false;
        }
    }

    public void onCheckBoxClicked(View v) {
        switch (v.getId()) {
            case R.id.bhk_one:
                oneSelected = setData((CheckBox) v, "1", restOne);
                break;
            case R.id.bhk_two:
                twoSelected = setData((CheckBox) v, "2", restTwo);
                break;
            case R.id.bhk_three:
                threeSelected = setData((CheckBox) v, "3", restThree);
                break;
            case R.id.bhk_four:
                threeSelected = setData((CheckBox) v, "4", restFour);
                break;
            case R.id.bhk_five:
                fiveSelected = setData((CheckBox) v, "5", restFive);
                break;
        }
    }
}
