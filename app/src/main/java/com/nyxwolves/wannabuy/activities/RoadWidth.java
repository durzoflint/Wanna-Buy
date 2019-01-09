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

public class RoadWidth extends AppCompatActivity {

    Button nextBtn;
    SeekBar roadWidth;
    TextView selectedWidth;

    int width = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_width);

        selectedWidth = findViewById(R.id.selected_width);

        nextBtn = findViewById(R.id.road_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(width  != 0){
                    Requirements.getInstance().roadWidth = String.valueOf(width);
                    if(Requirements.getInstance().isRentalIncome.equals(getString(R.string.yes))){
                        startActivity(new Intent(RoadWidth.this,ROIRentalActivity.class));
                    }else {
                        startActivity(new Intent(RoadWidth.this, RequirementName.class));
                    }
                }

            }
        });

        roadWidth = findViewById(R.id.road_width_seekbar);
        roadWidth.setMax(201);
        roadWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                width = progress;
                if(progress  == seekBar.getMax()){
                    String displayText = String.valueOf(progress-1)+" +";
                    selectedWidth.setText(displayText);
                }else{
                    selectedWidth.setText(String.valueOf(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
