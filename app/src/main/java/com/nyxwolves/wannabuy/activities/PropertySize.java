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

    TextView modeHeader;
    EditText minLandArea, maxLandArea, minBuiltUpArea, maxBuiltUpArea;
    Button nextButton;
    Spinner unitSpinner;
    ConstraintLayout builtUpAreaLayout, landAreaLayout;
    boolean onlyBuiltUpVisible = false;
    boolean onlyLandVisible = false;
    boolean bothVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_size);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        modeHeader = findViewById(R.id.mode_header);
        modeHeader.setText(Requirements.getInstance().buyorRent);

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
            bothVisible = true;

        } else if (Requirements.getInstance().subType.equals(getString(R.string.residential_apartments)) ||
                Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace)) ||
                Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace))) {
            landAreaLayout.setVisibility(View.GONE);
            builtUpAreaLayout.setVisibility(View.VISIBLE);
            onlyBuiltUpVisible = true;
            //setLimits(100);

        } else {
            landAreaLayout.setVisibility(View.VISIBLE);
            builtUpAreaLayout.setVisibility(View.GONE);
            onlyLandVisible = true;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //setLimits(position);
        if (position == 0) {
            minLandArea.setHint("in Sq.Ft");
            maxLandArea.setHint("in Sq.Ft");
        }else if(position == 1){
            minLandArea.setHint("in Grounds");
            maxLandArea.setHint("in Grounds");
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
                if(onlyBuiltUpVisible){
                    if(checkData(minBuiltUpArea,maxBuiltUpArea)){
                        Requirements.getInstance().minSizeBuilding = minBuiltUpArea.getText().toString().trim();
                        Requirements.getInstance().maxSizeBuilding = maxBuiltUpArea.getText().toString().trim();
                        startIntent();
                    }else{
                        Toast.makeText(PropertySize.this,"Enter the  property size",Toast.LENGTH_SHORT).show();
                    }
                }else if(onlyLandVisible){
                    if(checkData(minLandArea,maxLandArea)){
                        Requirements.getInstance().minSizeLand = minLandArea.getText().toString().trim();
                        Requirements.getInstance().maxSizeLand = maxLandArea.getText().toString().trim();
                        startIntent();
                    }else{
                        Toast.makeText(PropertySize.this,"Enter the  property size",Toast.LENGTH_SHORT).show();
                    }
                }else if(bothVisible){
                    if(checkData(minLandArea,maxLandArea) && checkData(minBuiltUpArea,maxBuiltUpArea)){
                        Requirements.getInstance().minSizeBuilding = minBuiltUpArea.getText().toString().trim();
                        Requirements.getInstance().maxSizeBuilding = maxBuiltUpArea.getText().toString().trim();
                        Requirements.getInstance().minSizeLand = minLandArea.getText().toString().trim();
                        Requirements.getInstance().maxSizeLand = maxLandArea.getText().toString().trim();
                        startIntent();
                    }else{
                        Toast.makeText(PropertySize.this,"Invalid Size.Please check your inputs",Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    private void startIntent(){

        if (Requirements.getInstance().subType.equals(getString(R.string.residential_land)) ||
                Requirements.getInstance().subType.equals(getString(R.string.commercial_land)) ||
                Requirements.getInstance().subType.equals(getString(R.string.institutional_land)) ||
                Requirements.getInstance().subType.equals(getString(R.string.industrial_land)) ||
                Requirements.getInstance().subType.equals(getString(R.string.farm_land))) {
            startActivity(new Intent(PropertySize.this, FacingActivity.class));
        } else {
            startActivity(new Intent(PropertySize.this, Budget.class));
        }
    }

    private boolean checkData(EditText inputMin,EditText inputMax){
        try{
            if(Integer.valueOf(inputMin.getText().toString()) > 0 && Integer.valueOf(inputMax.getText().toString()) > 0 ){
                if(Integer.valueOf(inputMax.getText().toString()) > Integer.valueOf(inputMin.getText().toString())){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }catch(NumberFormatException e){
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
