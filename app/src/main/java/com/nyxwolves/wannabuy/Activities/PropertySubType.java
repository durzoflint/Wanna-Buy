package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;


public class PropertySubType extends AppCompatActivity implements View.OnClickListener{

    RadioGroup residentialGroup,commercialGroup,industrialGroup;
    Button continueBtn;
    RadioButton selectedButton;
    TextView subtypeHeader, modeHeader;
    String subPropertyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_sub_type);

        modeHeader = findViewById(R.id.sub_type_mode_header);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        subtypeHeader =  findViewById(R.id.subtype_header);

        residentialGroup = findViewById(R.id.radioGroup_residential);
        commercialGroup = findViewById(R.id.radioGroup_commercial);
        industrialGroup = findViewById(R.id.radioGroup_industrial);

        continueBtn = findViewById(R.id.sub_type_next_btn);
        continueBtn.setOnClickListener(this);

        showRadioGroup();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sub_type_next_btn:
                if(subPropertyType != null) {
                    Requirements.getInstance().subType = subPropertyType;
                    Intent i = new Intent(PropertySubType.this,PropertySize.class);
                    startActivity(i);
                }
                break;
        }
    }

    private void showRadioGroup(){
        String property_type = getIntent().getStringExtra(getString(R.string.PROPERTY_TYPE));
        if(property_type.equals(getString(R.string.residential))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.VISIBLE);
            commercialGroup.setVisibility(View.GONE);
            industrialGroup.setVisibility(View.GONE);
            selectedButton = findViewById(R.id.resi_house);
            selectedButton.setChecked(false);
        }else if(property_type.equals(getString(R.string.commercial))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.GONE);
            commercialGroup.setVisibility(View.VISIBLE);
            industrialGroup.setVisibility(View.GONE);
            selectedButton = findViewById(R.id.comm_land);
            selectedButton.setChecked(false);
        }else if(property_type.equals(getString(R.string.industrial))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.GONE);
            commercialGroup.setVisibility(View.GONE);
            industrialGroup.setVisibility(View.VISIBLE);
            selectedButton = findViewById(R.id.indus_land);
            selectedButton.setChecked(false);
        }
    }

    public void radioButtonClicked(View v){
        switch (v.getId()){
            case R.id.resi_house:
                subPropertyType = getString(R.string.house);
                break;
            case R.id.resi_villa:
                subPropertyType = getString(R.string.villa);
                break;
            case R.id.resi_apartments:
                subPropertyType = getString(R.string.apartments);
                break;
            case R.id.resi_land:
                subPropertyType = getString(R.string.land);
                break;
            case R.id.comm_land:
                subPropertyType = getString(R.string.land);
                break;
            case R.id.comm_showroom:
                subPropertyType = getString(R.string.showroom);
                break;
            case R.id.comm_office_space:
                subPropertyType = getString(R.string.office_space);
                break;
            case R.id.indus_cold_storage:
                subPropertyType = getString(R.string.cold_storage);
                break;
            case R.id.indus_factory:
                subPropertyType = getString(R.string.factory);
                break;
            case R.id.indus_warehouse:
                subPropertyType = getString(R.string.warehouse);
                break;
            case R.id.indus_land:
                subPropertyType = getString(R.string.land);
                break;
        }
        Requirements.getInstance().subType = subPropertyType;
    }
}

