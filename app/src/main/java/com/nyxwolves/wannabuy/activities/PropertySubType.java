package com.nyxwolves.wannabuy.activities;

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

    RadioGroup residentialGroup,commercialGroup,industrialGroup,institutionalGroup;
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
        institutionalGroup = findViewById(R.id.radio_group_institutional);

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

                    if(subPropertyType.equals(getString(R.string.residential_land))){
                        startActivity(new Intent(PropertySubType.this,PropertySize.class));

                    }else if(subPropertyType.equals(getString(R.string.apartments))){
                        startActivity(new Intent(PropertySubType.this,FlooringActivity.class));

                    }else if(subPropertyType.equals(getString(R.string.commercial_land))){
                        startActivity(new Intent(PropertySubType.this,PropertySize.class));

                    }else if(subPropertyType.equals(getString(R.string.house)) || subPropertyType.equals(getString(R.string.villa))){
                        startActivity(new Intent(PropertySubType.this,Bhk.class));

                    }else if(subPropertyType.equals(getString(R.string.floorspace)) ||
                            subPropertyType.equals(getString(R.string.school)) ||
                            subPropertyType.equals(getString(R.string.hospital)) ||
                            subPropertyType.equals(getString(R.string.college))){
                        startActivity(new Intent(PropertySubType.this,CarParking.class));

                    }else if(subPropertyType.equals(getString(R.string.institutional_land))){
                        startActivity(new Intent(PropertySubType.this,PropertySize.class));
                    }

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
            institutionalGroup.setVisibility(View.GONE);
            selectedButton = findViewById(R.id.resi_house);
            selectedButton.setChecked(false);
        }else if(property_type.equals(getString(R.string.commercial))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.GONE);
            commercialGroup.setVisibility(View.VISIBLE);
            industrialGroup.setVisibility(View.GONE);
            institutionalGroup.setVisibility(View.GONE);
            selectedButton = findViewById(R.id.comm_floor_space);
            selectedButton.setChecked(false);
        }else if(property_type.equals(getString(R.string.industrial))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.GONE);
            commercialGroup.setVisibility(View.GONE);
            industrialGroup.setVisibility(View.VISIBLE);
            institutionalGroup.setVisibility(View.GONE);
            selectedButton = findViewById(R.id.indus_factory);
            selectedButton.setChecked(false);
        }else if(property_type.equals(getString(R.string.institutional))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.GONE);
            commercialGroup.setVisibility(View.GONE);
            industrialGroup.setVisibility(View.GONE);
            institutionalGroup.setVisibility(View.VISIBLE);
            selectedButton = findViewById(R.id.ins_school);
            selectedButton.setChecked(false);
        }
    }

    public void radioButtonClicked(View v){
        switch (v.getId()){
            case R.id.resi_house:
                subPropertyType = getString(R.string.house);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.resi_villa:
                subPropertyType = getString(R.string.villa);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.resi_apartments:
                subPropertyType = getString(R.string.apartments);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.resi_land:
                subPropertyType = getString(R.string.residential_land);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.comm_floor_space:
                subPropertyType = getString(R.string.floorspace);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.comm_independent:
                subPropertyType = getString(R.string.independent);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.comm_land:
                subPropertyType = getString(R.string.commercial_land);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.indus_cold_storage:
                subPropertyType = getString(R.string.cold_storage);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.indus_factory:
                subPropertyType = getString(R.string.factory);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.indus_warehouse:
                subPropertyType = getString(R.string.warehouse);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.ins_school:
                subPropertyType = getString(R.string.school);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.ins_college:
                subPropertyType = getString(R.string.college);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.ins_hospital:
                subPropertyType = getString(R.string.hospital);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.ins_land:
                subPropertyType = getString(R.string.institutional_land);
                Requirements.getInstance().subType = subPropertyType;
                break;
        }
    }
}

