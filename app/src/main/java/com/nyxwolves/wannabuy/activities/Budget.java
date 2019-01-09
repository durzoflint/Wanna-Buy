package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class Budget extends AppCompatActivity {

    SeekBar minBudgetSeekBar, maxBudgetSeekBar;
    Button nextButton;
    TextView minSelectedPrice, maxSelectedPrice, modeHeader;
    TextView minMinPrice, minMaxPrice;
    TextView maxMinPrice, maxMaxPrice;
    Spinner minPriceUnitSpinner, maxPriceUnitSpinner;

    int minBudget = 0;
    int maxBudget = 0;
    int minUnit = 0;
    int maxUnit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //min price unit spinner
        minPriceUnitSpinner = findViewById(R.id.min_price_unit_spinner);
        ArrayAdapter minAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit, android.R.layout.simple_spinner_item);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minPriceUnitSpinner.setAdapter(minAdapter);
        minPriceUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    minUnit = 0;
                    minMinPrice.setText("0 Thousand");
                    minMaxPrice.setText("1 Lakh");
                    Requirements.getInstance().minBudgetUnit = parent.getSelectedItem().toString();
                }else if(position == 1){
                    minUnit = 1;
                    minMinPrice.setText("0 Lakh");
                    minMaxPrice.setText("1 Crore");
                    Requirements.getInstance().minBudgetUnit = parent.getSelectedItem().toString();
                }else if(position == 2){
                    minUnit = 2;
                    minMinPrice.setText("0 Crore");
                    minMaxPrice.setText("100 Crores");
                    Requirements.getInstance().minBudgetUnit = parent.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //max price unit spinner
        maxPriceUnitSpinner = findViewById(R.id.max_price_unit_spinner);
        ArrayAdapter maxAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit, android.R.layout.simple_spinner_item);
        maxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxPriceUnitSpinner.setAdapter(maxAdapter);
        maxPriceUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    maxUnit = 0;
                    maxMinPrice.setText("0 Thousand");
                    maxMaxPrice.setText("1 Lakh");
                    Requirements.getInstance().maxBudgetUnit = parent.getSelectedItem().toString();
                }else if(position == 1){
                    maxUnit = 1;
                    maxMinPrice.setText("0 Lakh");
                    maxMaxPrice.setText("1 Crore");
                    Requirements.getInstance().minBudgetUnit = parent.getSelectedItem().toString();
                }else if(position == 2){
                    maxUnit = 2;
                    maxMinPrice.setText("0 Crore");
                    maxMaxPrice.setText("100 Crores");
                    Requirements.getInstance().minBudgetUnit = parent.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        modeHeader = findViewById(R.id.budget_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        minSelectedPrice = findViewById(R.id.min_selected_price);
        maxSelectedPrice = findViewById(R.id.max_selected_price);

        minMinPrice = findViewById(R.id.min_min_price);
        minMaxPrice = findViewById(R.id.min_max_price);

        maxMinPrice = findViewById(R.id.max_min_price);
        maxMaxPrice = findViewById(R.id.max_max_price);

        final boolean isRent = Requirements.getInstance().buyorRent.equals(getString(R.string.rent_text));

        if (isRent) {
            minMinPrice.setText("0 Thousand");
            minMaxPrice.setText("10 Lakhs");

            maxMinPrice.setText("0 Thousand");
            maxMaxPrice.setText("10 Lakhs");
        }
        minBudgetSeekBar = findViewById(R.id.min_budget_seekbar);
        minBudgetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minBudget = progress;
                if(minUnit == 0){
                    if(progress == 100) {
                        minSelectedPrice.setText(String.valueOf(progress/100) + " Lakh");
                    }else if(progress > 100){
                        minSelectedPrice.setText(String.valueOf(progress/100) + "+ Lakh");
                    }else{
                        minSelectedPrice.setText(String.valueOf(progress) + " Thousands");
                    }
                }else if(minUnit == 1){
                    if(progress == 100) {
                        minSelectedPrice.setText(String.valueOf(progress/100) + " Crore");
                    }else if(progress > 100){
                        minSelectedPrice.setText(String.valueOf(progress/100) + "+ Crore");
                    }else{
                        minSelectedPrice.setText(String.valueOf(progress) + " Lakhs");
                    }
                }else{
                    if(progress > 100){
                        minSelectedPrice.setText(String.valueOf(progress-1) + "+ Crores");
                    }else{
                        minSelectedPrice.setText(String.valueOf(progress)+" Crores");
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        minBudgetSeekBar.setMax(101);


        maxBudgetSeekBar = findViewById(R.id.max_budget_seekbar);
        maxBudgetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxBudget = progress;
                if(maxUnit == 0){
                    if(progress == 100) {
                        maxSelectedPrice.setText(String.valueOf(progress/100) + " Lakh");
                    }else if(progress > 100){
                        maxSelectedPrice.setText(String.valueOf(progress/100) + "+ Lakh");
                    }else{
                        maxSelectedPrice.setText(String.valueOf(progress) + " Thousands");
                    }
                }else if(maxUnit == 1){
                    if(progress == 100) {
                        maxSelectedPrice.setText(String.valueOf(progress/100) + " Crore");
                    }else if(progress > 100){
                        maxSelectedPrice.setText(String.valueOf(progress/100) + "+ Crore");
                    }else{
                        maxSelectedPrice.setText(String.valueOf(progress) + " Lakhs");
                    }
                }else{
                    if(progress > 100){
                        maxSelectedPrice.setText(String.valueOf(progress-1) + "+ Crores");
                    }else{
                        maxSelectedPrice.setText(String.valueOf(progress)+" Crores");
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        maxBudgetSeekBar.setMax(101);

        nextButton = findViewById(R.id.budget_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (minBudget != 0 && maxBudget != 0) {
                    if (minBudget < maxBudget) {
                        Requirements.getInstance().minBudget = String.valueOf(minBudget);
                        Requirements.getInstance().maxBudget = String.valueOf(maxBudget);

                        if (Requirements.getInstance().subType.equals(getString(R.string.residential_independent)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.commercial_independent)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.factory)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.cold_storage)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.warehouse)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.institutional_building)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.pg_rent_independent))) {
                            startActivity(new Intent(Budget.this, FurnishedOrNot.class));

                        } else if (Requirements.getInstance().subType.equals(getString(R.string.apartments)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.pg_rent_apartment))) {
                            startActivity(new Intent(Budget.this, Bhk.class));

                        } else if (Requirements.getInstance().subType.equals(getString(R.string.residential_land)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.commercial_land)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.industrial_land)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.institutional_land)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.farm_land))) {
                            startActivity(new Intent(Budget.this, PropertySize.class));
                        }

                    }
                } else {
                    Toast.makeText(Budget.this, "Max Budget should be more than Min Budget", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().minBudget = getString(R.string.not_set_text);
        Requirements.getInstance().maxBudget = getString(R.string.not_set_text);
    }
}

