package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class Building extends Activity {

    Button nextBtn;
    RadioButton factoryBtn,wareHouseBtn,coldStorageBtn;
    RadioButton schoolBtn,collegeBtn,hospitalBtn;
    RadioGroup resiGroup,commGroup,indusGroup,instiGroup;
    ConstraintLayout farmLandLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        resiGroup = findViewById(R.id.resi_building_group);
        commGroup = findViewById(R.id.comm_building_group);
        indusGroup = findViewById(R.id.indus_building_group);
        instiGroup = findViewById(R.id.ins_building_group);

        factoryBtn = findViewById(R.id.indus_factory);
        wareHouseBtn = findViewById(R.id.indus_ware_house);
        coldStorageBtn = findViewById(R.id.indus_cold_storage);

        farmLandLayout = findViewById(R.id.farm_land_layout);

        schoolBtn = findViewById(R.id.ins_school);
        collegeBtn = findViewById(R.id.ins_college);
        hospitalBtn = findViewById(R.id.ins_hospital);

        showGroup();

        nextBtn = findViewById(R.id.building_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Requirements.getInstance().subType.equals(getString(R.string.residential_independent))||
                        Requirements.getInstance().subType.equals(getString(R.string.apartments))){
                    startActivity(new Intent(Building.this,PropertySize.class));

                }else if(Requirements.getInstance().subType.equals(getString(R.string.apartments))){
                    startActivity(new Intent(Building.this,FlooringActivity.class));

                }else if(Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace))||
                        Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace))){
                    startActivity(new Intent(Building.this,PropertySize.class));

                }else if(Requirements.getInstance().subType.equals(getString(R.string.commercial_independent)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.industrial_independent))){
                    startActivity(new Intent(Building.this,PropertySize.class));
                }else if(Requirements.getInstance().type.equals(getString(R.string.farm_land))){
                    startActivity(new Intent(Building.this,Budget.class));
                }

            }
        });
    }
    private void showGroup(){
        String propertyType =  Requirements.getInstance().type;
        if(propertyType.equals(getString(R.string.commercial))){
            commGroup.setVisibility(View.VISIBLE);
            resiGroup.setVisibility(View.GONE);
            instiGroup.setVisibility(View.GONE);
            indusGroup.setVisibility(View.GONE);
            farmLandLayout.setVisibility(View.GONE);
        }else if(propertyType.equals(getString(R.string.residential)) || propertyType.equals(getString(R.string.pg_rent))){
            commGroup.setVisibility(View.GONE);
            resiGroup.setVisibility(View.VISIBLE);
            instiGroup.setVisibility(View.GONE);
            indusGroup.setVisibility(View.GONE);
            farmLandLayout.setVisibility(View.GONE);
        }else if(propertyType.equals(getString(R.string.institutional))){
            commGroup.setVisibility(View.GONE);
            resiGroup.setVisibility(View.GONE);
            instiGroup.setVisibility(View.VISIBLE);
            indusGroup.setVisibility(View.GONE);
            farmLandLayout.setVisibility(View.GONE);
        }else if(propertyType.equals(getString(R.string.industrial))){
            commGroup.setVisibility(View.GONE);
            resiGroup.setVisibility(View.GONE);
            instiGroup.setVisibility(View.GONE);
            indusGroup.setVisibility(View.VISIBLE);
            farmLandLayout.setVisibility(View.GONE);
        }else if(propertyType.equals(getString(R.string.farm_land))){
            commGroup.setVisibility(View.GONE);
            resiGroup.setVisibility(View.GONE);
            instiGroup.setVisibility(View.GONE);
            indusGroup.setVisibility(View.GONE);
            farmLandLayout.setVisibility(View.VISIBLE);
        }
    }
    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.resi_independent:
                Requirements.getInstance().subType = getString(R.string.residential_independent);
                break;
            case R.id.resi_apartments:
                Requirements.getInstance().subType = getString(R.string.apartments);
                break;
            case R.id.comm_independent:
                Requirements.getInstance().subType = getString(R.string.commercial_independent);
                break;
            case R.id.comm_floorspace:
                Requirements.getInstance().subType = getString(R.string.commercial_floorspace);
                break;
            case R.id.indus_independent:
                factoryBtn.setVisibility(View.VISIBLE);
                coldStorageBtn.setVisibility(View.VISIBLE);
                wareHouseBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.indus_factory:
                Requirements.getInstance().subType  = getString(R.string.factory);
                break;
            case R.id.indus_cold_storage:
                Requirements.getInstance().subType  =  getString(R.string.cold_storage);
                break;
            case R.id.indus_ware_house:
                Requirements.getInstance().subType = getString(R.string.warehouse);
                break;
            case R.id.indus_floorspace:
                Requirements.getInstance().subType = getString(R.string.industrial_floorspace);
                break;
            case R.id.ins_independent:
                schoolBtn.setVisibility(View.VISIBLE);
                collegeBtn.setVisibility(View.VISIBLE);
                hospitalBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.ins_school:
                Requirements.getInstance().subType = getString(R.string.school);
                break;
            case R.id.ins_college:
                Requirements.getInstance().subType = getString(R.string.college);
                break;
            case R.id.ins_hospital:
                Requirements.getInstance().subType =  getString(R.string.hospital);
                break;
        }
    }

}
