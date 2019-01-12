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
    EditText restOne,restTwo,restThree,restFour,restFive;

    String BHK = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhk);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        modeHeader =  findViewById(R.id.bhk_mode);
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
                //if(!BHK.equals("0")){
                    Requirements.getInstance().bhk = BHK;
                    if(Requirements.getInstance().subType.equals(getString(R.string.residential_independent)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.pg_rent_independent))){
                        startActivity(new Intent(Bhk.this,CarParking.class));

                    }else if(Requirements.getInstance().subType.equals(getString(R.string.apartments)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.pg_rent_apartment)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace))){
                        startActivity(new Intent(Bhk.this,FlooringActivity.class));

                    }else{
                        startActivity(new Intent(Bhk.this,FacingActivity.class));

                    }

                //}else{
                    //Toast.makeText(Bhk.this,"Choose number of rooms",Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.bhk_one:
                if(((CheckBox)v).isChecked()){
                    restOne.setVisibility(View.VISIBLE);
                }else{
                    restOne.setVisibility(View.GONE);
                }
                break;
            case R.id.bhk_two:
                if(((CheckBox)v).isChecked()){
                    restTwo.setVisibility(View.VISIBLE);
                }else{
                    restTwo.setVisibility(View.GONE);
                }
                break;
            case R.id.bhk_three:
                if(((CheckBox)v).isChecked()){
                    restThree.setVisibility(View.VISIBLE);
                }else{
                    restThree.setVisibility(View.GONE);
                }
                break;
            case R.id.bhk_four:
                if(((CheckBox)v).isChecked()){
                    restFour.setVisibility(View.VISIBLE);
                }else{
                    restFour.setVisibility(View.GONE);
                }
                break;
            case R.id.bhk_five:
                if(((CheckBox)v).isChecked()){
                    restFive.setVisibility(View.VISIBLE);
                }else{
                    restFive.setVisibility(View.GONE);
                }
                break;
        }
    }
}
