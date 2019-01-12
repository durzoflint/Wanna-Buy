package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class PropertyType extends AppCompatActivity implements View.OnClickListener {

    Button continueBtn;
    RadioButton selectedProperty, pgRent, farmLand, rentalIncome;
    TextView modeHeader;

    String property_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_type);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //propertType = findViewById(R.id.radioGroup);

        modeHeader = findViewById(R.id.mode_header);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        continueBtn = findViewById(R.id.next_btn);
        continueBtn.setOnClickListener(this);

        pgRent = findViewById(R.id.pg_rent);
        farmLand = findViewById(R.id.farm_land);
        rentalIncome = findViewById(R.id.rental_income_btn);

        selectedProperty = findViewById(R.id.residential);

        if (Requirements.getInstance().buyorRent.equals(getString(R.string.BUY))) {
            farmLand.setVisibility(View.VISIBLE);
            rentalIncome.setVisibility(View.VISIBLE);
            pgRent.setVisibility(View.VISIBLE);
        } else {
            farmLand.setVisibility(View.VISIBLE);
            pgRent.setVisibility(View.VISIBLE);
            rentalIncome.setVisibility(View.GONE);
        }
    }

    public void radioButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.residential:
                property_type = getString(R.string.residential);
                break;
            case R.id.commercial:
                property_type = getString(R.string.commercial);
                break;
            case R.id.industrial:
                property_type = getString(R.string.industrial);
                break;
            case R.id.instutional:
                property_type = getString(R.string.institutional);
                break;
            case R.id.pg_rent:
                property_type = getString(R.string.pg_rent);
                break;
            case R.id.rental_income_btn:
                property_type = getString(R.string.rental_income);
                break;
            case R.id.farm_land:
                property_type = getString(R.string.farm_land);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_btn:
                if (property_type != null) {
                    Requirements.getInstance().type = property_type;

                    if (property_type.equals(getString(R.string.farm_land))) {
                        Requirements.getInstance().subType = property_type;
                        startActivity(new Intent(PropertyType.this, Building.class));

                    } else if (property_type.equals(getString(R.string.pg_rent))) {
                        Requirements.getInstance().type = property_type;
                        startActivity(new Intent(PropertyType.this, PgRentOptions.class));

                    } else if (property_type.equals(getString(R.string.rental_income))) {
                        Requirements.getInstance().isRentalIncome = getString(R.string.yes);
                        startActivity(new Intent(PropertyType.this, Building.class));
                    } else {
                        startActivity(new Intent(PropertyType.this, PropertySubType.class));
                    }
                } else {
                    Toast.makeText(PropertyType.this, "Select anyone", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().type = getString(R.string.not_set_text);
        if (property_type != null) {
            if (property_type.equals(getString(R.string.farm_land))) {
                Requirements.getInstance().subType = getString(R.string.not_set_text);
            }
        }
    }
}
