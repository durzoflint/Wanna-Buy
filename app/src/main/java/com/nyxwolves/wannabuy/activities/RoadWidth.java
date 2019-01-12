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
    SeekBar minRoadWidth,maxRoadWidth;
    TextView minSelectedWidth,maxSelectedWidth;

    int minWidth = 0;
    int maxWidth = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_width);

        minSelectedWidth = findViewById(R.id.selected_width);
        maxSelectedWidth = findViewById(R.id.max_selected_width);

        nextBtn = findViewById(R.id.road_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(minWidth  != 0&& maxWidth != 0){
                    Requirements.getInstance().minRoadWidth = String.valueOf(minWidth);
                    Requirements.getInstance().maxRoadWidth = String.valueOf(maxWidth);
                    if(Requirements.getInstance().isRentalIncome.equals(getString(R.string.yes))){
                        startActivity(new Intent(RoadWidth.this,ROIRentalActivity.class));
                    }else {
                        startActivity(new Intent(RoadWidth.this, RequirementName.class));
                    }
                }

            }
        });

        minRoadWidth = findViewById(R.id.road_width_seekbar);
        minRoadWidth.setMax(201);
        minRoadWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minWidth = progress;
                if(progress  == seekBar.getMax()){
                    String displayText = String.valueOf(progress-1)+" +";
                    minSelectedWidth.setText(displayText);
                }else{
                    minSelectedWidth.setText(String.valueOf(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxRoadWidth = findViewById(R.id.max_road_seekbar);
        maxRoadWidth.setMax(201);
        maxRoadWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxWidth = progress;
                if(progress  == seekBar.getMax()){
                    String displayText = String.valueOf(progress-1)+" +";
                    maxSelectedWidth.setText(displayText);
                }else{
                    maxSelectedWidth.setText(String.valueOf(progress));
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
