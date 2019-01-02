package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void oncheckBoxClicked(View v){

    }
}
