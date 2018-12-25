package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nyxwolves.wannabuy.R;

public class ROIRentalActivity extends AppCompatActivity {

    Button nextBtn;
    EditText roiInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roirental);

        roiInput = findViewById(R.id.roi_input);

        nextBtn = findViewById(R.id.rent_roi_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(roiInput.getText().toString().trim().length() > 0){
                    startActivity(new Intent(ROIRentalActivity.this,RentalInvestment.class));
                }
            }
        });
    }
}
