package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
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
    TextView greaterThanTextMin,greaterThanTextMax;
    Spinner minPriceUnitSpinner, maxPriceUnitSpinner;
    RadioGroup minGreaterThanTen, maxGreaterThanTen;
    int minBudget = 0;
    int maxBudget = 0;
    String minUnit, minNextUnit, maxUnit, maxNextUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        minGreaterThanTen = findViewById(R.id.min_greater_than);
        maxGreaterThanTen = findViewById(R.id.max_greater_than);

        //min price unit spinner
        minPriceUnitSpinner = findViewById(R.id.min_price_unit_spinner);
        ArrayAdapter minAdapter;
        if (Requirements.getInstance().buyorRent.equals(getString(R.string.rent_text))) {
            minAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit_rent, android.R.layout.simple_spinner_item);
        } else {
            minAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit_buy, android.R.layout.simple_spinner_item);
        }

        //max price unit spinner
        maxPriceUnitSpinner = findViewById(R.id.max_price_unit_spinner);
        ArrayAdapter maxAdapter;

        if (Requirements.getInstance().buyorRent.equals(getString(R.string.rent_text))) {
            maxAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit_rent, android.R.layout.simple_spinner_item);
        } else {
            maxAdapter = ArrayAdapter.createFromResource(this, R.array.price_unit_buy, android.R.layout.simple_spinner_item);
        }

        modeHeader = findViewById(R.id.budget_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        minSelectedPrice = findViewById(R.id.min_selected_price);
        maxSelectedPrice = findViewById(R.id.max_selected_price);

        minMinPrice = findViewById(R.id.min_min_price);
        minMaxPrice = findViewById(R.id.min_max_price);

        maxMinPrice = findViewById(R.id.max_min_price);
        maxMaxPrice = findViewById(R.id.max_max_price);

        greaterThanTextMax = findViewById(R.id.greater_than_text_max);
        greaterThanTextMin = findViewById(R.id.greater_than_text_min);

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

        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minPriceUnitSpinner.setAdapter(minAdapter);
        minPriceUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                minBudgetSeekBar.setProgress(0);
                minUnit = parent.getSelectedItem().toString();
                if (minUnit.equals("Lakhs")) {
                    minBudgetSeekBar.setMax(101);
                }
                String minDisplayText = "0 " + parent.getSelectedItem();
                minMinPrice.setText(minDisplayText);

                if(isRent){
                    if (minUnit.equals("Lakhs")) {
                        Log.d("MIN_PRICE","LESS 10");
                        greaterThanTextMin.setText(getString(R.string.greater_than_10_lakhs));
                        minGreaterThanTen.setVisibility(View.VISIBLE);
                    } else {
                        minGreaterThanTen.setVisibility(View.GONE);
                    }
                }else{
                    if (minUnit.equals("Crores")) {
                        greaterThanTextMin.setText(getString(R.string.greater_than_10_crores));
                        minGreaterThanTen.setVisibility(View.VISIBLE);
                    } else {
                        minGreaterThanTen.setVisibility(View.GONE);
                    }
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
                    if (maxUnit != null) {
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

        maxAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maxPriceUnitSpinner.setAdapter(maxAdapter);
        maxPriceUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maxBudgetSeekBar.setProgress(0);
                maxUnit = parent.getSelectedItem().toString();
                if (maxUnit.equals("Lakhs")) {
                    maxBudgetSeekBar.setMax(101);
                }
                String minDisplayText = "0 " + parent.getSelectedItem();
                maxMinPrice.setText(minDisplayText);

                if(isRent){
                    if (maxUnit.equals("Lakhs")) {
                        greaterThanTextMax.setText(getString(R.string.greater_than_10_lakhs));
                        maxGreaterThanTen.setVisibility(View.VISIBLE);
                    } else {
                        maxGreaterThanTen.setVisibility(View.GONE);
                    }
                }else{
                    if (maxUnit.equals("Crores")) {
                        greaterThanTextMax.setText(getString(R.string.greater_than_10_crores));
                        maxGreaterThanTen.setVisibility(View.VISIBLE);
                    } else {
                        maxGreaterThanTen.setVisibility(View.GONE);
                    }
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

        nextButton = findViewById(R.id.budget_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (maxBudget != 0) {
                    if (checkPrice()) {
                        Requirements.getInstance().minBudget = String.valueOf(minBudget);
                        Requirements.getInstance().maxBudget = String.valueOf(maxBudget);
                        Requirements.getInstance().minBudgetUnit = minUnit;
                        Requirements.getInstance().maxBudgetUnit = maxUnit;

                        if (Requirements.getInstance().subType.equals(getString(R.string.residential_apartments)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.pg_rent_apartment))) {
                            startActivity(new Intent(Budget.this, Bhk.class));

                        } else if (Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace))) {
                            startActivity(new Intent(Budget.this, FlooringActivity.class));

                        } else if (Requirements.getInstance().subType.equals(getString(R.string.residential_land)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.commercial_land)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.industrial_land)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.institutional_land)) ||
                                Requirements.getInstance().subType.equals(getString(R.string.farm_land))) {
                            startActivity(new Intent(Budget.this, PropertySize.class));
                        } else {
                            startActivity(new Intent(Budget.this, FurnishedOrNot.class));
                        }
                    }
                } else {
                    Toast.makeText(Budget.this, "Invalid Price.Please Check.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPrice() {
        if (Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))) {
            if (minUnit.equals("Crores") && maxUnit.equals("Crores")) {
                if (minBudget <= maxBudget) {
                    return true;
                } else {
                    return false;
                }
            } else if (minUnit.equals("Lakhs") && maxUnit.equals("Crores")) {
                if (maxBudget > 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (minUnit.equals("Lakhs") && maxUnit.equals("Lakhs")) {
                if (maxBudget > 0 && minBudget < maxBudget) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (minUnit.equals("Lakhs") && maxUnit.equals("Lakhs")) {
                if (minBudget <= maxBudget) {
                    return true;
                } else {
                    return false;
                }
            } else if (minUnit.equals("Thousands") && maxUnit.equals("Lakhs")) {
                if (maxBudget > 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (minUnit.equals("Thousands") && maxUnit.equals("Thousands")) {
                if (maxBudget > 0 && maxBudget > minBudget) {
                    return true;
                } else {
                    return false;
                }
            } else if (minUnit.equals("Lakhs") && maxUnit.equals("Lakhs")) {
                if (maxBudget > 0 && maxBudget > minBudget) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

    }

    public void onRadioButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.min_greater_yes:
                minBudgetSeekBar.setMax(101);
                break;
            case R.id.min_greater_no:
                minBudgetSeekBar.setMax(10);
                if(minUnit.equals("Crores")){
                    minMaxPrice.setText("10 Crores");
                }else{
                    minMaxPrice.setText("10 Lakhs");
                }

                break;
            case R.id.max_greater_yes:
                maxBudgetSeekBar.setMax(101);
                break;
            case R.id.max_greater_no:
                maxBudgetSeekBar.setMax(10);
                if(minUnit.equals("Crores")){
                    maxMaxPrice.setText("10 Crores");
                }else{
                    maxMaxPrice.setText("10 Lakhs");
                }
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

