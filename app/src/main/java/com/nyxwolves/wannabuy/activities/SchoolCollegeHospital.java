package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class SchoolCollegeHospital extends AppCompatActivity {

    Button nextButton;
    TextView modeHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_college_hospital);

        modeHeader = findViewById(R.id.mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        nextButton = findViewById(R.id.school_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SchoolCollegeHospital.this,PropertySize.class));
            }
        });
    }

    public void onCheckBoxClicked(View v){

    }
}
