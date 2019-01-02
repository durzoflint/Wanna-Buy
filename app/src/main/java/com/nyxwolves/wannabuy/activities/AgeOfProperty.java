package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class AgeOfProperty extends AppCompatActivity {

    Button nextBtn;
    SeekBar minAgeBar,maxAgeBar;
    TextView minSelectedAge,maxSelectedAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_of_property);

        minSelectedAge = findViewById(R.id.min_selected_age);
        maxSelectedAge = findViewById(R.id.max_selected_age);

        minAgeBar =  findViewById(R.id.min_age_bar);
        minAgeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Requirements.getInstance().minAge = String.valueOf(progress);
                minSelectedAge.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxAgeBar = findViewById(R.id.max_age_bar);
        maxAgeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Requirements.getInstance().maxAge = String.valueOf(progress);
                maxSelectedAge.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        nextBtn = findViewById(R.id.age_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgeOfProperty.this,AmmenitiesActivity.class));
            }
        });
    }
}
