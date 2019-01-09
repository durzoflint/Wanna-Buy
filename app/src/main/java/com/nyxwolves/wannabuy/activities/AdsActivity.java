package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdsActivity extends AppCompatActivity implements View.OnClickListener,Spinner.OnItemSelectedListener{


    TextInputEditText cityInput,doorNumberInput,addressInput;
    EditText landArea,builtUpArea;
    Button picUploadBtn,paymentBtn;
    Button sellBtn,rentBtn;
    CircleImageView propertyPic;
    CheckBox covCarParking,unCovParking;
    NumberPicker covPicker,unCovPicker;
    RadioGroup resiSub,commSub,insSub,indusSub;
    RadioGroup rentalResiSub,rentalCommSub,rentalInsSub,rentalIndusSub;
    RadioGroup resiBuild,commBuild,indusBuild;
    RadioGroup rentalResiBuild,rentalCommBuild,rentalIndusBuild;
    RadioGroup indusIndependentGroup,rentalIndusIndependentGroup;
    ConstraintLayout rentalStart,rentalEnd,advanceDeposit,bhkLayout,carParkingLayout,ammenitiesLayout,furnishedLayout;
    ConstraintLayout builtUpAreaLayout,landAreaLayout;
    FrameLayout pgRentLayout,rentalIncomeType;
    RadioButton farmLand,pgRentButton,rentalIncomeButton;
    LinearLayout roiLayout,petsLayout;
    Spinner areaUnitSpinner;

    final int IMAGE_REQ=1002;
    final int LOCATION_REQUEST = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        areaUnitSpinner = findViewById(R.id.ads_unit_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.unit_spinner,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaUnitSpinner.setAdapter(adapter);
        areaUnitSpinner.setOnItemSelectedListener(this);

        sellBtn = findViewById(R.id.sell_btn);
        rentBtn = findViewById(R.id.rent_btn);

        rentalIncomeType = findViewById(R.id.rental_income_prop_type);

        builtUpAreaLayout = findViewById(R.id.built_up_area_layout);
        landAreaLayout = findViewById(R.id.land_area_layout);

        //roiLayout
        roiLayout = findViewById(R.id.roi_layout);

        //petsLayout
        petsLayout = findViewById(R.id.pets_allowed_layout);

        //radioButtons
        farmLand = findViewById(R.id.ads_farm_land);
        pgRentButton = findViewById(R.id.ads_pg_rent);
        rentalIncomeButton = findViewById(R.id.ads_rental_income);

        //property size input
        landArea = findViewById(R.id.land_size_input);
        builtUpArea = findViewById(R.id.built_area_input);

        //CAR PARKING COVERED
        covCarParking = findViewById(R.id.covered_parking_check);
        covPicker = findViewById(R.id.cov_picker);
        covPicker.setMaxValue(100);
        covPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SellerAd.getInstance().covCarParkingNum = String.valueOf(newVal);
            }
        });

        //UNCOVERED CAR PARKING
        unCovParking = findViewById(R.id.un_cov_car_park);
        unCovPicker = findViewById(R.id.uncov_picker);
        unCovPicker.setMaxValue(100);
        unCovPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SellerAd.getInstance().unCovCarParkingNum = String.valueOf(newVal);
            }
        });

        rentalStart = findViewById(R.id.lease_start_layout);
        rentalEnd = findViewById(R.id.lease_end_layout);
        advanceDeposit = findViewById(R.id.advance_layout);

        bhkLayout = findViewById(R.id.bhk_layout);
        carParkingLayout = findViewById(R.id.car_parking_layout);
        ammenitiesLayout = findViewById(R.id.ammenities_layout);
        furnishedLayout = findViewById(R.id.furnished_layout);

        resiSub = findViewById(R.id.resi_sub);
        resiBuild = findViewById(R.id.resi_building_group);

        rentalResiSub = findViewById(R.id.rental_resi_sub);
        rentalResiBuild = findViewById(R.id.rental_resi_building_group);

        commSub = findViewById(R.id.comm_sub);
        commBuild = findViewById(R.id.comm_building_group);

        rentalCommSub = findViewById(R.id.rental_comm_sub);
        rentalCommBuild = findViewById(R.id.rental_comm_building_group);

        insSub = findViewById(R.id.ins_sub);
        rentalInsSub = findViewById(R.id.rental_ins_sub);

        indusSub = findViewById(R.id.indus_sub);
        indusBuild = findViewById(R.id.indus_building_group);
        indusIndependentGroup = findViewById(R.id.indus_independent_sub_group);
        rentalIndusIndependentGroup = findViewById(R.id.rental_indus_independent_sub_group);

        rentalIndusSub = findViewById(R.id.rental_indus_sub);
        rentalIndusBuild = findViewById(R.id.rental_indus_building_group);

        pgRentLayout =findViewById(R.id.pg_rent_layout);

        cityInput = findViewById(R.id.ads_area_input);
        cityInput.setOnClickListener(this);

        doorNumberInput = findViewById(R.id.ads_door_input);
        addressInput = findViewById(R.id.ads_address_input);

        propertyPic = findViewById(R.id.prop_pic);

        picUploadBtn = findViewById(R.id.upload_btn);
        picUploadBtn.setOnClickListener(this);

        paymentBtn = findViewById(R.id.payment_btn);
        paymentBtn.setOnClickListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        SellerAd.getInstance().areaUnit = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.upload_btn:
                chooseImage();
                break;
            case R.id.payment_btn:
                if(checkAddress() && checkArea() && checkDoor()){
                    Intent i = new Intent(AdsActivity.this,HomeActivity.class);
                    i.setAction(getString(R.string.POST_AD));
                    startActivity(i);
                }
                break;
            case R.id.ads_area_input:
                getLocation();
                break;
        }
    }

    private void getLocation(){
        try {
            AutocompleteFilter filter = new AutocompleteFilter.Builder().setCountry("IN").build();
            Intent locationIntent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(filter)
                    .build(this);
            startActivityForResult(locationIntent, LOCATION_REQUEST);
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(this, "Google Play Services missing", Toast.LENGTH_SHORT).show();
        } catch (GooglePlayServicesRepairableException e) {
            Toast.makeText(this, "Google Play Services error", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean checkAddress(){
        if(addressInput.getText().toString().length() > 0){
            SellerAd.getInstance().propertyAddress = addressInput.getText().toString().trim();
            return true;
        }else {
            return false;
        }
    }

    private boolean checkArea(){
        if(cityInput.getText().toString().length() > 0){
            SellerAd.getInstance().area = cityInput.getText().toString().trim();
            return true;
        }else{
            return false;
        }
    }

    private boolean checkDoor(){
        if(doorNumberInput.getText().toString().length() > 0){
            SellerAd.getInstance().doorNo = doorNumberInput.getText().toString().trim();
            return true;
        }else{
            return false;
        }
    }

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.sell_btn:
                SellerAd.getInstance().sellOrRent = getString(R.string.SELL);
                farmLand.setVisibility(View.VISIBLE);
                rentalIncomeButton.setVisibility(View.VISIBLE);
                pgRentButton.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                petsLayout.setVisibility(View.GONE);
                break;
            case R.id.rent_btn:
                SellerAd.getInstance().sellOrRent = getString(R.string.RENT);
                farmLand.setVisibility(View.GONE);
                rentalIncomeButton.setVisibility(View.GONE);
                pgRentButton.setVisibility(View.VISIBLE);
                roiLayout.setVisibility(View.GONE);
                break;
            case R.id.resi_radio_btn:
                resiSub.setVisibility(View.VISIBLE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                indusIndependentGroup.setVisibility(View.GONE);
                break;
            case R.id.comm_radio_btn:
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.VISIBLE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                indusIndependentGroup.setVisibility(View.GONE);
                break;
            case R.id.indus_radio_btn:
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.VISIBLE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                indusIndependentGroup.setVisibility(View.GONE);
                break;
            case R.id.indus_floorspace:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_indus_floorspace:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                break;
            case R.id.indus_independent:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                indusIndependentGroup.setVisibility(View.VISIBLE);
                break;
            case R.id.rental_indus_independent:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                rentalIndusIndependentGroup.setVisibility(View.VISIBLE);
                break;
            case R.id.ins_radio_btn:
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.VISIBLE);
                indusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.ins_land:
                builtUpAreaLayout.setVisibility(View.GONE);
                break;
            case R.id.ins_building:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.ads_pg_rent:
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.VISIBLE);
                roiLayout.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                break;
            case R.id.resi_building:
                resiBuild.setVisibility(View.VISIBLE);
                commBuild.setVisibility(View.GONE);
                indusBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.resi_land:
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.resi_independent:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.rental_resi_independent:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.resi_apartments:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                if(SellerAd.getInstance().sellOrRent.equals(getString(R.string.rent_text))){
                    petsLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rental_resi_apartments:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                petsLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.comm_building:
                resiBuild.setVisibility(View.GONE);
                commBuild.setVisibility(View.VISIBLE);
                indusBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.comm_land:
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.comm_independent:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.rental_comm_independent:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.comm_floorspace:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_comm_floorspace:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                break;
            case R.id.indus_land:
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.indus_building:
                resiBuild.setVisibility(View.GONE);
                commBuild.setVisibility(View.GONE);
                indusBuild.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.VISIBLE);
                indusIndependentGroup.setVisibility(View.GONE);
                break;
            case R.id.new_btn:
                SellerAd.getInstance().isNew = getString(R.string.new_text);
                break;
            case R.id.resale_btn:
                SellerAd.getInstance().isNew = getString(R.string.resale_text);
                break;
            case R.id.one_bhk:
                SellerAd.getInstance().bhk = String.valueOf(1);
                break;
            case R.id.two_bhk:
                SellerAd.getInstance().bhk = String.valueOf(2);
                break;
            case R.id.three_bhk:
                SellerAd.getInstance().bhk = String.valueOf(3);
                break;
            case R.id.four_bhk:
                SellerAd.getInstance().bhk = String.valueOf(4);
                break;
            case R.id.five_bhk:
                SellerAd.getInstance().bhk = String.valueOf(5);
                break;
            case R.id.north_btn:
                SellerAd.getInstance().facing = getString(R.string.north_text);
                break;
            case R.id.south_btn:
                SellerAd.getInstance().facing = getString(R.string.south_text);
                break;
            case R.id.west_btn:
                SellerAd.getInstance().facing = getString(R.string.west_text);
                break;
            case R.id.east_btn:
                SellerAd.getInstance().facing = getString(R.string.east_text);
                break;
            case R.id.furnished_btn:
                SellerAd.getInstance().furnished = getString(R.string.furnished);
                break;
            case R.id.un_furnished_btn:
                SellerAd.getInstance().furnished = getString(R.string.un_furnished);
                break;
            case R.id.semi_furnished_btn:
                SellerAd.getInstance().furnished = getString(R.string.semi_furnished);
                break;
            case R.id.negotiable_btn:
                SellerAd.getInstance().budgetNegotiable = getString(R.string.negotiable);
                Log.d("TEST","REACHED");
                break;
            case R.id.non_negotiable_btn:
                SellerAd.getInstance().budgetNegotiable = getString(R.string.non_negotiable);
                Log.d("TEST","REACH00ED");
                break;
            case R.id.ads_rental_income:
                roiLayout.setVisibility(View.VISIBLE);
                rentalIncomeType.setVisibility(View.VISIBLE);
                break;
            case R.id.rental_resi_radio_btn:
                rentalResiSub.setVisibility(View.VISIBLE);
                rentalCommSub.setVisibility(View.GONE);
                rentalInsSub.setVisibility(View.GONE);
                rentalIndusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_comm_radio_btn:
                rentalResiSub.setVisibility(View.GONE);
                rentalCommSub.setVisibility(View.VISIBLE);
                rentalInsSub.setVisibility(View.GONE);
                rentalIndusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_indus_radio_btn:
                rentalResiSub.setVisibility(View.GONE);
                rentalCommSub.setVisibility(View.GONE);
                rentalInsSub.setVisibility(View.GONE);
                rentalIndusSub.setVisibility(View.VISIBLE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_ins_radio_btn:
                rentalResiSub.setVisibility(View.GONE);
                rentalCommSub.setVisibility(View.GONE);
                rentalInsSub.setVisibility(View.VISIBLE);
                rentalIndusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_ads_pg_rent:
                rentalResiSub.setVisibility(View.GONE);
                rentalCommSub.setVisibility(View.GONE);
                rentalInsSub.setVisibility(View.GONE);
                rentalIndusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.VISIBLE);
                roiLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_resi_building:
                rentalResiBuild.setVisibility(View.VISIBLE);
                rentalCommBuild.setVisibility(View.GONE);
                rentalIndusBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.rental_resi_land:
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
            case R.id.rental_comm_building:
                rentalResiBuild.setVisibility(View.GONE);
                rentalCommBuild.setVisibility(View.VISIBLE);
                rentalIndusBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.rental_comm_land:
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_indus_land:
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_indus_building:
                rentalResiBuild.setVisibility(View.GONE);
                rentalCommBuild.setVisibility(View.GONE);
                rentalIndusBuild.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_REQ);
    }

    public void onCheckBoxClicked(View v){
        switch(v.getId()){
            case R.id.covered_parking_check:
                if(((CheckBox)v).isChecked()){
                    covPicker.setVisibility(View.VISIBLE);
                }else{
                    covPicker.setVisibility(View.GONE);
                }
                break;
            case R.id.un_cov_car_park:
                if(((CheckBox)v).isChecked()){
                    unCovPicker.setVisibility(View.VISIBLE);
                }else{
                    unCovPicker.setVisibility(View.GONE);
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_REQ && resultCode == RESULT_OK && data != null){
            propertyPic.setImageURI(data.getData());
        }
        if(requestCode == LOCATION_REQUEST && resultCode == RESULT_OK){
            Place place = PlaceAutocomplete.getPlace(AdsActivity.this,data);
            cityInput.setText(place.getName().toString());
        }
    }

}
