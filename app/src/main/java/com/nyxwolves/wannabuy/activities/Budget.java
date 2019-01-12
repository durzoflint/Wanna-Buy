package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
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
    RadioGroup minGreaterThanTenCrores, maxGreaterThanTenCrores;
    int minBudget = 0;
    int maxBudget = 0;
    String minUnit, minNextUnit, maxUnit, maxNextUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        minGreaterThanTenCrores = findViewById(R.id.min_greater_than);
        maxGreaterThanTenCrores = findViewById(R.id.max_greater_than);

        //min price unit spinner
        minPriceUnitSpinner = findViewById(R.id.min_price_unit_spinner);
        ArrayAdapter minAdapter;
        if (Requirements.getInstance().buyorRent.equals(getString(R.string.rent_text))) {
            minAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit_rent, android.R.layout.simple_spinner_item);
        } else {
            minAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit_buy, android.R.layout.simple_spinner_item);
        }

        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minPriceUnitSpinner.setAdapter(minAdapter);
        minPriceUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minUnit = parent.getSelectedItem().toString();
                String minDisplayText = "0 " + parent.getSelectedItem();
                minMinPrice.setText(minDisplayText);
                if (minUnit.equals("Crores")) {
                    minGreaterThanTenCrores.setVisibility(View.VISIBLE);
                } else {
                    minGreaterThanTenCrores.setVisibility(View.GONE);
                }
                try {
                    minNextUnit = parent.getItemAtPosition(position + 1).toString();
                    minMaxPrice.setText("1+ " + minNextUnit);
                } catch (ArrayIndexOutOfBoundsException e) {
                    minNextUnit = parent.getItemAtPosition(position).toString();
                    minMaxPrice.setText("100+ " + minNextUnit);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //max price unit spinner
        maxPriceUnitSpinner = findViewById(R.id.max_price_unit_spinner);
        ArrayAdapter maxAdapter;

        if (Requirements.getInstance().buyorRent.equals(getString(R.string.rent_text))) {
            maxAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit_rent, android.R.layout.simple_spinner_item);
        } else {
            maxAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit_buy, android.R.layout.simple_spinner_item);
        }

        maxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxPriceUnitSpinner.setAdapter(maxAdapter);
        maxPriceUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maxUnit = parent.getSelectedItem().toString();
                String minDisplayText = "0 " + parent.getSelectedItem();
                maxMinPrice.setText(minDisplayText);
                if (maxUnit.equals("Crores")) {
                    maxGreaterThanTenCrores.setVisibility(View.VISIBLE);
                } else {
                    maxGreaterThanTenCrores.setVisibility(View.GONE);
                }
                try {
                    maxNextUnit = parent.getItemAtPosition(position + 1).toString();
                    maxMaxPrice.setText("1+ " + maxNextUnit);
                } catch (ArrayIndexOutOfBoundsException e) {
                    maxNextUnit = parent.getItemAtPosition(position).toString();
                    maxMaxPrice.setText("100+ " + maxNextUnit);
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
                if (progress == 100) {
                    if (minUnit.equals(minNextUnit)) {
                        minSelectedPrice.setText(String.valueOf(progress) + minNextUnit);
                    } else {
                        minSelectedPrice.setText(String.valueOf(progress / 100) + minNextUnit);
                    }
                } else if (progress > 100) {
                    if (minUnit.equals(minNextUnit)) {
                        minSelectedPrice.setText(String.valueOf(progress - 1) + "+ " + minNextUnit);
                    } else {
                        minSelectedPrice.setText(String.valueOf(progress / 100) + "+ " + minNextUnit);
                    }
                } else {
                    if (minUnit != null) {
                        minSelectedPrice.setText(String.valueOf(progress) + minUnit);
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

                if (progress == 100) {
                    if (maxUnit.equals(maxNextUnit)) {
                        maxSelectedPrice.setText(String.valueOf(progress) + maxNextUnit);
                    } else {
                        maxSelectedPrice.setText(String.valueOf(progress / 100) + maxNextUnit);
                    }

                } else if (progress > 100) {
                    if (maxUnit.equals(maxNextUnit)) {
                        maxSelectedPrice.setText(String.valueOf(progress - 1) + "+ " + maxNextUnit);
                    } else {
                        maxSelectedPrice.setText(String.valueOf(progress / 100) + "+ " + maxNextUnit);
                    }
                } else {
                    if (minUnit != null) {
                        maxSelectedPrice.setText(String.valueOf(progress) + maxUnit);
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
                    if (checkPrice()) {
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
                                Requirements.getInstance().subType.equals(getString(R.string.pg_rent_independent)) ||
                                Requirements.getInstance().rentalResi.equals(getString(R.string.yes)) ||
                                Requirements.getInstance().rentalComm.equals(getString(R.string.yes)) ||
                                Requirements.getInstance().rentalIns.equals(getString(R.string.yes)) ||
                                Requirements.getInstance().rentalIndus.equals(getString(R.string.yes)) ||
                                Requirements.getInstance().rentalPgApartments.equals(getString(R.string.yes))) {
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

    private boolean checkPrice(){
        if(minUnit.equals("Crores") && maxUnit.equals("Crores")){
            if(minBudget <= maxBudget){
                return true;
            }else{
                return false;
            }
        }else if(minUnit.equals("Lakhs") && maxUnit.equals("Crores")){
            if(maxBudget > 0){
                return true;
            }else{
                return false;
            }
        }else if(minUnit.equals("Lakhs") && maxUnit.equals("Lakhs")){
            if(maxBudget >0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public void onRadioButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.min_greater_yes:
                minBudgetSeekBar.setMax(101);
                break;
            case R.id.min_greater_no:
                minBudgetSeekBar.setMax(10);
                minMaxPrice.setText("10 Crores");
                break;
            case R.id.max_greater_yes:
                maxBudgetSeekBar.setMax(101);
                break;
            case R.id.max_greater_no:
                maxBudgetSeekBar.setMax(10);
                maxMaxPrice.setText("10 Crores");
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().minBudget = getString(R.string.not_set_text);
        Requirements.getInstance().maxBudget = getString(R.string.not_set_text);
    }
}

