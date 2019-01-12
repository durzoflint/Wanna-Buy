package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;


public class PropertySubType extends AppCompatActivity implements View.OnClickListener{

    RadioGroup residentialGroup,commercialGroup,industrialGroup,institutionalGroup;
    Button continueBtn;
    TextView subtypeHeader, modeHeader;
    String subPropertyType;
    LinearLayout indusLandSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_sub_type);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        indusLandSub =  findViewById(R.id.indus_land_sub);

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

                    if(subPropertyType.equals(getString(R.string.residential_land)) ||
                            subPropertyType.equals(getString(R.string.commercial_land)) ||
                            subPropertyType.equals(getString(R.string.industrial_land))){
                        startActivity(new Intent(PropertySubType.this,Budget.class));

                    }else if(subPropertyType.equals(getString(R.string.residential_building))){
                        startActivity(new Intent(PropertySubType.this,NewOrResale.class));

                    }else if(subPropertyType.equals(getString(R.string.commercial_building))){
                        startActivity(new Intent(PropertySubType.this,Building.class));

                    }else if(subPropertyType.equals(getString(R.string.industrial_building))){
                        startActivity(new Intent(PropertySubType.this,Building.class));

                    }else if(subPropertyType.equals(getString(R.string.institutional_land))){
                        startActivity(new Intent(PropertySubType.this,PropertySize.class));

                    }else if(subPropertyType.equals(getString(R.string.institutional_building))){
                        startActivity(new Intent(PropertySubType.this,SchoolCollegeHospital.class));

                    }else if(subPropertyType.equals(getString(R.string.farm_land))){
                        startActivity(new Intent(PropertySubType.this,Building.class));
                    }
                }
                break;
        }
    }

    private void showRadioGroup(){
        String property_type = Requirements.getInstance().type;
        if(property_type.equals(getString(R.string.residential))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.VISIBLE);
            commercialGroup.setVisibility(View.GONE);
            industrialGroup.setVisibility(View.GONE);
            institutionalGroup.setVisibility(View.GONE);

        }else if(property_type.equals(getString(R.string.commercial))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.GONE);
            commercialGroup.setVisibility(View.VISIBLE);
            industrialGroup.setVisibility(View.GONE);
            institutionalGroup.setVisibility(View.GONE);
        }else if(property_type.equals(getString(R.string.industrial))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.GONE);
            commercialGroup.setVisibility(View.GONE);
            industrialGroup.setVisibility(View.VISIBLE);
            institutionalGroup.setVisibility(View.GONE);
        }else if(property_type.equals(getString(R.string.institutional))){
            subtypeHeader.setText("What type of "+property_type+" Property");
            residentialGroup.setVisibility(View.GONE);
            commercialGroup.setVisibility(View.GONE);
            industrialGroup.setVisibility(View.GONE);
            institutionalGroup.setVisibility(View.VISIBLE);
        }
    }

    public void radioButtonClicked(View v){
        switch (v.getId()){

            case R.id.resi_land:
                subPropertyType = getString(R.string.residential_land);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.resi_building:
                subPropertyType = getString(R.string.residential_building);
                break;
            case R.id.comm_land:
                subPropertyType = getString(R.string.commercial_land);
                Requirements.getInstance().subType = subPropertyType;
                break;
            case R.id.comm_building:
                subPropertyType=  getString(R.string.commercial_building);
                break;
            case R.id.indus_land:
                subPropertyType = getString(R.string.industrial_land);
                indusLandSub.setVisibility(View.VISIBLE);
                break;
            case R.id.indus_building:
                subPropertyType = getString(R.string.industrial_building);
                indusLandSub.setVisibility(View.GONE);
                break;
            case R.id.ins_land:
                subPropertyType = getString(R.string.institutional_land);
                break;
            case R.id.ins_building:
                subPropertyType  = getString(R.string.institutional_building);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().subType = getString(R.string.not_set_text);
    }
}




