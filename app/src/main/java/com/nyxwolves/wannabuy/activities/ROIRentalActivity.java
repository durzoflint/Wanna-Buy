package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class ROIRentalActivity extends AppCompatActivity {

    Button nextBtn;
    EditText roiInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roirental);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        roiInput = findViewById(R.id.roi_input);

        nextBtn = findViewById(R.id.rent_roi_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(roiInput.getText().toString().trim().length() > 0){
                    Requirements.getInstance().roi = roiInput.getText().toString();
                    startActivity(new Intent(ROIRentalActivity.this,RequirementName.class));
                }
            }
        });
    }
}
