package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class Bhk extends AppCompatActivity {

    Button nextBtn;
    TextView modeHeader;

    String BHK = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhk);

        modeHeader =  findViewById(R.id.bhk_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        nextBtn = findViewById(R.id.bhk_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!BHK.equals("0")){
                    Requirements.getInstance().bhk = BHK;
                    if(Requirements.getInstance().subType.equals(getString(R.string.residential_independent))){
                        startActivity(new Intent(Bhk.this,CarParking.class));

                    }else if(Requirements.getInstance().subType.equals(getString(R.string.apartments))){
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

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.one_bhk:
                BHK = "1";
                break;
            case R.id.two_bhk:
                BHK = "2";
                break;
            case R.id.three_bhk:
                BHK = "3";
                break;
            case R.id.four_bhk:
                BHK = "4";
                break;
            case R.id.five_bhk:
                BHK = "5";
                break;
        }
    }
}
