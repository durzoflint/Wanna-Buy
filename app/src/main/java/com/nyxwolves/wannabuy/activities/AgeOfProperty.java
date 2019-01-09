package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class AgeOfProperty extends AppCompatActivity {

    Button nextBtn;
    SeekBar minAgeBar,maxAgeBar;
    TextView minSelectedAge,maxSelectedAge;

    int minAge = 0;
    int maxAge = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_of_property);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        minSelectedAge = findViewById(R.id.min_selected_age);
        maxSelectedAge = findViewById(R.id.max_selected_age);

        minAgeBar =  findViewById(R.id.min_age_bar);
        minAgeBar.setMax(101);
        minAgeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minAge = progress;
                if(progress == seekBar.getMax()){
                    String displayText = String.valueOf(progress-1)+" +";
                    minSelectedAge.setText(displayText);
                }else{
                    minSelectedAge.setText(String.valueOf(progress));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxAgeBar = findViewById(R.id.max_age_bar);
        maxAgeBar.setMax(101);
        maxAgeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxAge = progress;
                if(progress == seekBar.getMax()){
                    String displayText  = String.valueOf(progress-1)+" +";
                    maxSelectedAge.setText(displayText);
                }else{
                    maxSelectedAge.setText(String.valueOf(progress));
                }
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
                if(minAge != 0 || maxAge != 0) {
                    Requirements.getInstance().minAge = String.valueOf(minAge);
                    Requirements.getInstance().maxAge = String.valueOf(maxAge);

                    startActivity(new Intent(AgeOfProperty.this, AmmenitiesActivity.class));
                }
            }
        });
    }
}
