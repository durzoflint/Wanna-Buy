package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class PropertySize extends AppCompatActivity implements View.OnClickListener{

    TextView minMinSize, minMaxSize, maxMinSize,maxMaxSize,modeHeader,minSelectedSize, maxSelectedSize;
    SeekBar minSizeSeekBar,maxSizeSeekBar;
    Button nextButton;

    int minSizeOfProperty, maxSizeOfProperty;
    String propertyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_size);

        modeHeader = findViewById(R.id.mode_header);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        minMinSize = findViewById(R.id.min_size);
        minMaxSize = findViewById(R.id.max_size);
        minSelectedSize = findViewById(R.id.selected_size);

        maxMinSize = findViewById(R.id.max_min_size);
        maxMaxSize = findViewById(R.id.max_max_size);
        maxSelectedSize = findViewById(R.id.max_selected_size);

        minSizeSeekBar = findViewById(R.id.min_area_seekbar);
        minSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minSizeOfProperty = progress;
                minSelectedSize.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxSizeSeekBar = findViewById(R.id.max_area_seekbar);
        maxSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxSizeOfProperty = progress;
                //maxSelectedSize.setText(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        nextButton = findViewById(R.id.size_next_btn);
        nextButton.setOnClickListener(this);



        propertyType = Requirements.getInstance().type;
        setLimits(propertyType);
    }

    private void setLimits(String option) {
        minMinSize.setText("0 sq. Ft");
        maxMinSize.setText("0 sq, Ft");

        if (option.equals(getString(R.string.residential))) {
            minMaxSize.setText("10000 sq. Ft");
            maxMaxSize.setText("10000 sq. Ft");

            minSizeSeekBar.setMax(10000);
            maxSizeSeekBar.setMax(10000);
        } else if (option.equals(getString(R.string.commercial))) {
            minMaxSize.setText("100000+ sq. Ft");
            maxMaxSize.setText("100000+ sq. Ft");

            minSizeSeekBar.setMax(100000);
            maxSizeSeekBar.setMax(100000);

        } else if (option.equals(getString(R.string.industrial))) {
            minMaxSize.setText("100000+ sq. Ft");
            maxMaxSize.setText("100000+ sq. Ft");

            minSizeSeekBar.setMax(100000);
            maxSizeSeekBar.setMax(100000);
        } else if (option.equals(getString(R.string.institutional))) {
            minMaxSize.setText("100000+ sq. Ft");
            maxMaxSize.setText("100000+ sq. Ft");

            minSizeSeekBar.setMax(100000);
            maxSizeSeekBar.setMax(100000);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.size_next_btn:
                if (minSizeOfProperty != 0 && maxSizeOfProperty != 0) {
                    Requirements.getInstance().minSize = String.valueOf(minSizeOfProperty);
                    Requirements.getInstance().maxSize = String.valueOf(maxSizeOfProperty);

                    startActivity(new Intent(PropertySize.this,FacingActivity.class));
                } else {
                    Toast.makeText(PropertySize.this, "Choose the size of property", Toast.LENGTH_SHORT).show();
                }
        }
    }

   }
