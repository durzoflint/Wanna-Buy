package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class Building extends Activity {

    Button nextBtn;
    CheckBox factoryBtn,wareHouseBtn,coldStorageBtn,doNotDeviateShowRoom,groundFloorShowroom;
    RadioButton schoolBtn,collegeBtn,hospitalBtn;
    RadioGroup resiGroup,commGroup,indusGroup,instiGroup,indusIndependentSub;
    LinearLayout rentalGroup;
    LinearLayout rentalResiSub,rentalCommSub,rentalIndusSub,rentalInsSub,rentalPgSub;
    ConstraintLayout farmLandLayout;
    CheckBox showRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        resiGroup = findViewById(R.id.resi_building_group);
        commGroup = findViewById(R.id.comm_building_group);
        indusGroup = findViewById(R.id.indus_building_group);
        instiGroup = findViewById(R.id.ins_building_group);
        rentalGroup = findViewById(R.id.rental_income_group);

        rentalResiSub = findViewById(R.id.rental_resi_sub_layout);
        rentalCommSub = findViewById(R.id.rental_comm_sub_layout);
        rentalInsSub = findViewById(R.id.rental_ins_sub_layout);
        rentalIndusSub = findViewById(R.id.rental_indus_sub_layout);
        rentalPgSub = findViewById(R.id.rental_pg_apartments_layout);

        indusIndependentSub = findViewById(R.id.indus_independent_sub);

        factoryBtn = findViewById(R.id.indus_factory);
        wareHouseBtn = findViewById(R.id.indus_ware_house);
        coldStorageBtn = findViewById(R.id.indus_cold_storage);

        farmLandLayout = findViewById(R.id.farm_land_layout);

        schoolBtn = findViewById(R.id.ins_school);
        collegeBtn = findViewById(R.id.ins_college);
        hospitalBtn = findViewById(R.id.ins_hospital);

        showRoom = findViewById(R.id.comm_show_room);

        doNotDeviateShowRoom = findViewById(R.id.show_room_do_not_deviate);
        groundFloorShowroom = findViewById(R.id.show_room_ground_floor);

        showGroup();

        nextBtn = findViewById(R.id.building_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Requirements.getInstance().subType.equals(getString(R.string.residential_independent))||
                        Requirements.getInstance().subType.equals(getString(R.string.residential_apartments)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.pg_rent_independent)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.pg_rent_apartment))){
                    startActivity(new Intent(Building.this,PropertySize.class));

                }else if(Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace))||
                        Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace))){
                    startActivity(new Intent(Building.this,PropertySize.class));

                }else if(Requirements.getInstance().subType.equals(getString(R.string.commercial_independent)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.industrial_independent))){
                    startActivity(new Intent(Building.this,PropertySize.class));

                }else if(Requirements.getInstance().type.equals(getString(R.string.farm_land))){
                    startActivity(new Intent(Building.this,Budget.class));
                }else if(Requirements.getInstance().subType.equals(getString(R.string.factory)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.cold_storage)) ||
                        Requirements.getInstance().subType.equals(getString(R.string.warehouse))){
                    startActivity(new Intent(Building.this,PropertySize.class));

                }else if(Requirements.getInstance().isRentalIncome.equals(getString(R.string.yes))){
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
            doNotDeviateShowRoom.setVisibility(View.GONE);
        }else if(propertyType.equals(getString(R.string.institutional))){
            commGroup.setVisibility(View.GONE);
            resiGroup.setVisibility(View.GONE);
            instiGroup.setVisibility(View.VISIBLE);
            indusGroup.setVisibility(View.GONE);
            farmLandLayout.setVisibility(View.GONE);
            doNotDeviateShowRoom.setVisibility(View.GONE);
        }else if(propertyType.equals(getString(R.string.industrial))){
            commGroup.setVisibility(View.GONE);
            resiGroup.setVisibility(View.GONE);
            instiGroup.setVisibility(View.GONE);
            indusGroup.setVisibility(View.VISIBLE);
            farmLandLayout.setVisibility(View.GONE);
            doNotDeviateShowRoom.setVisibility(View.GONE);
        }else if(propertyType.equals(getString(R.string.farm_land))){
            commGroup.setVisibility(View.GONE);
            resiGroup.setVisibility(View.GONE);
            instiGroup.setVisibility(View.GONE);
            indusGroup.setVisibility(View.GONE);
            farmLandLayout.setVisibility(View.VISIBLE);
            doNotDeviateShowRoom.setVisibility(View.GONE);
        }else if(propertyType.equals(getString(R.string.rental_income))){
            commGroup.setVisibility(View.GONE);
            resiGroup.setVisibility(View.GONE);
            instiGroup.setVisibility(View.GONE);
            indusGroup.setVisibility(View.GONE);
            farmLandLayout.setVisibility(View.GONE);
            rentalGroup.setVisibility(View.VISIBLE);
            doNotDeviateShowRoom.setVisibility(View.GONE);
        }
    }

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.resi_independent:
                if(Requirements.getInstance().type.equals(getString(R.string.pg_rent))){
                    Requirements.getInstance().subType = getString(R.string.pg_rent_independent);
                }else{
                    Requirements.getInstance().subType = getString(R.string.residential_independent);
                }
                break;
            case R.id.resi_apartments:
                if(Requirements.getInstance().type.equals(getString(R.string.pg_rent))){
                    Requirements.getInstance().subType = getString(R.string.pg_rent_apartment);
                }else {
                    Requirements.getInstance().subType = getString(R.string.residential_apartments);
                }
                break;
            case R.id.comm_independent:
                Requirements.getInstance().subType = getString(R.string.commercial_independent);
                showRoom.setVisibility(View.VISIBLE);
                break;
            case R.id.comm_floorspace:
                Requirements.getInstance().subType = getString(R.string.commercial_floorspace);
                showRoom.setVisibility(View.VISIBLE);
                doNotDeviateShowRoom.setVisibility(View.VISIBLE);
                groundFloorShowroom.setVisibility(View.VISIBLE);
                break;
            case R.id.indus_independent:
                indusIndependentSub.setVisibility(View.VISIBLE);
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
                indusIndependentSub.setVisibility(View.GONE);
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

    private String setData(CheckBox checkBox,LinearLayout layout){
        if(checkBox.isChecked()){
            layout.setVisibility(View.VISIBLE);
            return getString(R.string.yes);
        }else{
            layout.setVisibility(View.GONE);
            return getString(R.string.no);
        }
    }

    public void onCheckBoxClicked(View v){
        switch(v.getId()){
            case R.id.rental_resi:
                Requirements.getInstance().rentalResi = setData(((CheckBox)v),rentalResiSub);
                break;
            case R.id.rental_comm:
                Requirements.getInstance().rentalComm = setData(((CheckBox)v),rentalCommSub);
                break;
            case R.id.rental_ins:
                Requirements.getInstance().rentalIns = setData(((CheckBox)v),rentalInsSub);
                break;
            case R.id.rental_indus:
                Requirements.getInstance().rentalIndus = setData(((CheckBox)v),rentalIndusSub);
                break;
            case R.id.rental_farm_land:
                if(((CheckBox)v).isChecked()){
                    Requirements.getInstance().rentalFarmLand = getString(R.string.yes);
                }else{
                    Requirements.getInstance().rentalFarmLand = getString(R.string.no);
                }
                break;
            case R.id.rental_pg_rent_service_apartment:
                Requirements.getInstance().rentalPgApartments = setData(((CheckBox)v),rentalPgSub);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Requirements.getInstance().subType = getString(R.string.not_set_text);
        Requirements.getInstance().rentalResi = getString(R.string.not_set_text);
        Requirements.getInstance().rentalComm = getString(R.string.not_set_text);
        Requirements.getInstance().rentalIndus = getString(R.string.not_set_text);
        Requirements.getInstance().rentalIns = getString(R.string.not_set_text);
        Requirements.getInstance().rentalFarmLand = getString(R.string.not_set_text);
        Requirements.getInstance().rentalPgApartments = getString(R.string.not_set_text);
    }
}
