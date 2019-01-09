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

public class FurnishedOrNot extends AppCompatActivity{

   Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furnished_or_not);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nextBtn = findViewById(R.id.furnished_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Requirements.getInstance().subType.equals(getString(R.string.residential_independent)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.pg_rent_independent))){
                    startActivity(new Intent(FurnishedOrNot.this,Bhk.class));

                }else{
                    startActivity(new Intent(FurnishedOrNot.this,CarParking.class));

                }

            }
        });

    }

    public void radioButtonClicked(View v){

    }
}
