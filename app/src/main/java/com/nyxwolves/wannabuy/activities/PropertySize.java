package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class PropertySize extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView minMinSize, minMaxSize, maxMinSize, maxMaxSize, modeHeader, minSelectedSize, maxSelectedSize;
    //SeekBar minSizeSeekBar,maxSizeSeekBar;
    //SeekBar minSizeSeekBarBuiltArea,maxSizeSeekBarBuiltArea;
    EditText minLandArea, maxLandArea, minBuiltUpArea, maxBuiltUpArea;
    TextView minMinSizeBuilt, minMaxSizeBuilt, maxMinSizeBuilt, maxMaxSizeBuilt, minSelectedSizeBuilt, maxSelectedSizeBuilt;
    Button nextButton;
    Spinner unitSpinner;
    ConstraintLayout builtUpAreaLayout, landAreaLayout;
    int minSizeLand, maxSizeLand;
    int minSizeBuiltUp, maxSizeBuiltUp;
    boolean isBuiltUpVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_size);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        modeHeader = findViewById(R.id.mode_header);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        //for  land area
        /*minMinSize = findViewById(R.id.min_size);
        minMaxSize = findViewById(R.id.max_size);
        minSelectedSize = findViewById(R.id.selected_size);

        maxMinSize = findViewById(R.id.max_min_size);
        maxMaxSize = findViewById(R.id.max_max_size);
        maxSelectedSize = findViewById(R.id.max_selected_size);

        minSizeSeekBar = findViewById(R.id.min_area_seekbar);
        minSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minSizeLand = progress;
                if(progress == seekBar.getMax()){
                    String displayText = String.valueOf(progress-1)+" +";
                    minSelectedSize.setText(displayText);
                }else{
                    minSelectedSize.setText(String.valueOf(progress));
                }

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
                maxSizeLand = progress;
                if(progress == seekBar.getMax()){
                    String displayText = String.valueOf(progress-1)+" +";
                    maxSelectedSize.setText(displayText);
                }else{
                    maxSelectedSize.setText(String.valueOf(progress));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

        //for built_up area
        /*minMinSizeBuilt = findViewById(R.id.min_size_built_up);
        minMaxSizeBuilt = findViewById(R.id.max_size_built_up);
        minSelectedSizeBuilt = findViewById(R.id.selected_size_built_up);

        maxMinSizeBuilt = findViewById(R.id.max_min_size_built_up);
        maxMaxSizeBuilt = findViewById(R.id.max_max_size_built_up);
        maxSelectedSizeBuilt = findViewById(R.id.max_selected_size_built_up);

        minSizeSeekBarBuiltArea = findViewById(R.id.min_area_seekbar_built_up);
        minSizeSeekBarBuiltArea.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minSizeBuiltUp = progress;
                if(progress == seekBar.getMax()){
                    String displayText = String.valueOf(progress-1)+" +";
                    minSelectedSizeBuilt.setText(displayText);
                }else{
                    minSelectedSizeBuilt.setText(String.valueOf(progress));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maxSizeSeekBarBuiltArea = findViewById(R.id.max_area_seekbar_built_up);
        maxSizeSeekBarBuiltArea.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxSizeBuiltUp = progress;
                if(progress == seekBar.getMax()){
                    String displayText = String.valueOf(progress-1)+" +";
                    maxSelectedSizeBuilt.setText(displayText);
                }else{
                    maxSelectedSizeBuilt.setText(String.valueOf(progress));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

        //editexts
        minLandArea = findViewById(R.id.min_area_input);
        maxLandArea = findViewById(R.id.max_area_input);
        minBuiltUpArea = findViewById(R.id.min_built_up_input);
        maxBuiltUpArea = findViewById(R.id.max_built_up_input);

        //next button
        nextButton = findViewById(R.id.size_next_btn);
        nextButton.setOnClickListener(this);

        //spinner
        unitSpinner = findViewById(R.id.unit_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.unit_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);
        unitSpinner.setOnItemSelectedListener(this);

        //builtUparea
        builtUpAreaLayout = findViewById(R.id.built_up_area_layout);
        landAreaLayout = findViewById(R.id.land_area_layout);
        if (Requirements.getInstance().subType.equals(getString(R.string.residential_independent)) ||
                Requirements.getInstance().subType.equals(getString(R.string.commercial_independent)) ||
                Requirements.getInstance().subType.equals(getString(R.string.factory)) ||
                Requirements.getInstance().subType.equals(getString(R.string.cold_storage)) ||
                Requirements.getInstance().subType.equals(getString(R.string.warehouse)) ||
                Requirements.getInstance().subType.equals(getString(R.string.pg_rent_independent)) ||
                Requirements.getInstance().subType.equals(getString(R.string.institutional_building))) {
            landAreaLayout.setVisibility(View.VISIBLE);
            builtUpAreaLayout.setVisibility(View.VISIBLE);

        } else if (Requirements.getInstance().subType.equals(getString(R.string.apartments)) ||
                Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace)) ||
                Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace))) {
            landAreaLayout.setVisibility(View.GONE);
            builtUpAreaLayout.setVisibility(View.VISIBLE);
            //setLimits(100);

        } else {
            landAreaLayout.setVisibility(View.VISIBLE);
            builtUpAreaLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //setLimits(position);
        if (position == 0) {
            minLandArea.setHint("in Sq.Ft");
            maxLandArea.setHint("in Sq.Ft");
        }else if(position == 1){
            minLandArea.setHint("in Ground");
            maxLandArea.setHint("in Ground");
        }else if(position == 2){
            minLandArea.setHint("in Cents");
            maxLandArea.setHint("in Cents");
        }else if(position == 3){
            minLandArea.setHint("in Acres");
            maxLandArea.setHint("in Acres");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.size_next_btn:
                if (checkData(minLandArea,maxLandArea) || checkData(minBuiltUpArea,maxBuiltUpArea)) {

                    Requirements.getInstance().minSizeLand = minLandArea.getText().toString().trim();
                    Requirements.getInstance().maxSizeLand = maxLandArea.getText().toString().trim();
                    Requirements.getInstance().minSizeBuilding = minBuiltUpArea.getText().toString().trim();
                    Requirements.getInstance().maxSizeBuilding = maxBuiltUpArea.getText().toString().trim();

                    if (Requirements.getInstance().subType.equals(getString(R.string.residential_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.commercial_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.institutional_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.industrial_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.farm_land))) {
                        startActivity(new Intent(PropertySize.this, FacingActivity.class));
                    } else {
                        startActivity(new Intent(PropertySize.this, Budget.class));
                    }
                } else {
                    Toast.makeText(PropertySize.this, "Choose the size of property", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private boolean checkData(EditText inputMin,EditText inputMax){

       if(Integer.valueOf(inputMin.getText().toString().trim()) > 0 && Integer.valueOf(inputMax.getText().toString()) > 0 ){
           if(Integer.valueOf(inputMax.getText().toString().trim()) > Integer.valueOf(inputMin.getText().toString().trim())){
               return true;
           }else{
               return false;
           }
       }else{
           return false;
       }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().minSizeLand = getString(R.string.not_set_text);
        Requirements.getInstance().minSizeBuilding = getString(R.string.not_set_text);
        Requirements.getInstance().maxSizeLand = getString(R.string.not_set_text);
        Requirements.getInstance().maxSizeBuilding = getString(R.string.not_set_text);
    }

}
