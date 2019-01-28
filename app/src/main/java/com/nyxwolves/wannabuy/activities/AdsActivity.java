package com.nyxwolves.wannabuy.activities;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdsActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {


    TextInputEditText cityInput, doorNumberInput, addressInput;
    EditText flooringInput, bhkInput, noOfHouseInput, totalFloorsInput;
    EditText landAreaInput, builtUpArea, budgetInput, rentalIncrementalInput, roiInput, leaseStartInput, leaseEndInput, advanceInput, incrementCustomInput;
    Button picUploadBtn, paymentBtn;
    CircleImageView propertyPic;
    CheckBox covCarParking, unCovParking;
    NumberPicker covPicker, unCovPicker;
    RadioGroup resiSub, commSub, insSub, indusSub, pgRentSub, farmLandGroup;
    RadioGroup rentalResiSub, rentalCommSub, rentalInsSub, rentalIndusSub;
    RadioGroup resiBuild, commBuild, indusBuild;
    RadioGroup rentalResiBuild, rentalCommBuild, rentalIndusBuild, rentalFarmLandSub, rentalPgServiceSub;
    RadioGroup indusIndependentGroup, rentalIndusIndependentGroup;
    ConstraintLayout rentalStart, rentalEnd, advanceDeposit, bhkLayout, carParkingLayout, ammenitiesLayout, furnishedLayout, flooringLayout;
    ConstraintLayout builtUpAreaLayout, landAreaLayout, leaseOrRent;
    FrameLayout pgRentLayout, rentalIncomeType;
    RadioButton farmLand, pgRentButton, rentalIncomeButton;
    LinearLayout roiLayout, petsLayout, rentalIncrementalLayout, noOfHouse, tenantPreferances, totalFloors, vegNonVegLayout, showRoomLayout;
    LinearLayout propertySizeLayout;
    Spinner areaUnitSpinner;
    SeekBar roadWidth;
    TextView roadSelectedWidth, adsBudgetHeader;
    ImageView startIcon,endIcon;

    final int IMAGE_REQ = 1002;
    final int LOCATION_REQUEST = 1003;
    int roadWidthInt;
    boolean isBuiltUpVisible = false;
    boolean isLandAreaVisible = false;
    boolean isRentalIncome = false;
    boolean isStartDate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        areaUnitSpinner = findViewById(R.id.ads_unit_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.unit_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaUnitSpinner.setAdapter(adapter);
        areaUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SellerAd.getInstance().adsLandAreaUnit = parent.getSelectedItem().toString();
                landAreaInput.setHint(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        roadSelectedWidth = findViewById(R.id.selected_width);
        roadWidth = findViewById(R.id.ads_road_width_seekbar);
        roadWidth.setMax(201);
        roadWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                roadWidthInt = progress;
                if (progress > 200) {
                    String text = Integer.valueOf(progress - 1) + "+ Ft";
                    roadSelectedWidth.setText(text);
                } else {
                    String text = Integer.valueOf(progress) + " Ft";
                    roadSelectedWidth.setText(text);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //tenant preference
        tenantPreferances = findViewById(R.id.tenant_preference_layout);

        //veg or non-veg tenant
        vegNonVegLayout = findViewById(R.id.veg_non_veg_layout);

        //flooring and bhk input
        flooringInput = findViewById(R.id.floor_input);
        bhkInput = findViewById(R.id.bhk_input);
        noOfHouse = findViewById(R.id.no_of_house);
        noOfHouseInput = findViewById(R.id.no_of_house_input);

        totalFloors = findViewById(R.id.total_no_of_floor);
        totalFloorsInput = findViewById(R.id.total_floors_input);

        showRoomLayout = findViewById(R.id.showroom_layout);

        rentalIncomeType = findViewById(R.id.rental_income_prop_type);

        builtUpAreaLayout = findViewById(R.id.built_up_area_layout);
        landAreaLayout = findViewById(R.id.land_area_layout);
        propertySizeLayout = findViewById(R.id.property_size_layout);

        //rental income data layout
        startIcon = findViewById(R.id.icon_date_start);
        startIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStartDate = true;
                DatePickerDialog datePickerDialog  = new DatePickerDialog(AdsActivity.this,AdsActivity.this,2019,1,1);
                datePickerDialog.show();
            }
        });

        endIcon = findViewById(R.id.icon_date_end);
        endIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                isStartDate = false;
                DatePickerDialog datePickerDialog  = new DatePickerDialog(AdsActivity.this,AdsActivity.this,2019,1,1);
                datePickerDialog.show();
            }
        });

        roiLayout = findViewById(R.id.roi_layout);
        roiInput = findViewById(R.id.ads_roi_input);

        rentalStart = findViewById(R.id.lease_start_layout);
        leaseStartInput = findViewById(R.id.start_date);

        rentalEnd = findViewById(R.id.lease_end_layout);
        leaseEndInput = findViewById(R.id.end_date);

        advanceDeposit = findViewById(R.id.advance_layout);
        advanceInput = findViewById(R.id.advance_input);

        leaseOrRent = findViewById(R.id.lease_rent_layout);

        rentalIncrementalLayout = findViewById(R.id.rental_incremental_layout);
        rentalIncrementalInput = findViewById(R.id.rental_incremental_input);
        incrementCustomInput = findViewById(R.id.increment_custom_input);

        //budget
        budgetInput = findViewById(R.id.ads_budget_input);
        adsBudgetHeader = findViewById(R.id.ads_budget_header);

        //petsLayout
        petsLayout = findViewById(R.id.pets_allowed_layout);

        farmLandGroup = findViewById(R.id.farm_land_group);

        //radioButtons
        farmLand = findViewById(R.id.ads_farm_land);
        pgRentButton = findViewById(R.id.ads_pg_rent);
        rentalIncomeButton = findViewById(R.id.ads_rental_income);

        //property size input
        landAreaInput = findViewById(R.id.land_size_input);
        builtUpArea = findViewById(R.id.built_area_input);

        //CAR PARKING COVERED
        covCarParking = findViewById(R.id.covered_parking_check);
        covPicker = findViewById(R.id.cov_picker);
        covPicker.setMaxValue(10);
        covPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SellerAd.getInstance().adsCovCarParkingNum = String.valueOf(newVal);
            }
        });

        //UNCOVERED CAR PARKING
        unCovParking = findViewById(R.id.un_cov_car_park);
        unCovPicker = findViewById(R.id.uncov_picker);
        unCovPicker.setMaxValue(10);
        unCovPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SellerAd.getInstance().adsUnCovCarParkingNum = String.valueOf(newVal);
            }
        });

        bhkLayout = findViewById(R.id.bhk_layout);
        flooringLayout = findViewById(R.id.flooring_layout);
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

        rentalFarmLandSub = findViewById(R.id.rental_farm_land_group);
        rentalPgServiceSub = findViewById(R.id.rental_ads_pg_sub);

        indusSub = findViewById(R.id.indus_sub);
        indusBuild = findViewById(R.id.indus_building_group);
        indusIndependentGroup = findViewById(R.id.indus_independent_sub_group);
        rentalIndusIndependentGroup = findViewById(R.id.rental_indus_independent_sub_group);

        rentalIndusSub = findViewById(R.id.rental_indus_sub);
        rentalIndusBuild = findViewById(R.id.rental_indus_building_group);

        pgRentSub = findViewById(R.id.pg_rent_sub);

        pgRentLayout = findViewById(R.id.pg_rent_layout);

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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(isStartDate){
            String startDate = (month+1)+"-"+dayOfMonth+"-"+year;
            leaseStartInput.setText(startDate);
            SellerAd.getInstance().rentStartDate = startDate;
        }else{
            String endDate = (month+1)+"-"+dayOfMonth+"-"+year;
            leaseEndInput.setText(endDate);
            SellerAd.getInstance().rentEndDate = endDate;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.upload_btn:
                chooseImage();
                break;
            case R.id.payment_btn:
                if (true) {

                    Intent i = new Intent(AdsActivity.this, HomeActivity.class);
                    i.setAction(getString(R.string.POST_AD));
                    startActivity(i);

                } else {
                    Toast.makeText(AdsActivity.this, "Please Check the inputs", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ads_area_input:
                getLocation();
                break;
        }
    }


    /*private boolean checkData() {
        if (checkAddress() && checkLocation() && checkDoor()) {
            if (isBuiltUpVisible) {
                if (checkBuiltUpArea()) {

                }
            } else if (isLandAreaVisible) {
                if (checkLandArea()) {

                }
            } else if (isBuiltUpVisible && isLandAreaVisible) {
                if (checkLandArea() && checkBuiltUpArea()) {

                }
            }
        }else{
            return false;
        }
    }*/


    private void getLocation() {
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

    private boolean checkLandArea() {
        if (landAreaInput.getText().toString().trim().length() > 0) {
            SellerAd.getInstance().adsLandArea = landAreaInput.getText().toString().trim();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkBuiltUpArea() {
        if (builtUpArea.getText().toString().trim().length() > 0) {
            SellerAd.getInstance().adsBuiltUpArea = builtUpArea.getText().toString().trim();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkBhk() {
        if (bhkInput.getText().toString().trim().length() > 0) {
            SellerAd.getInstance().adsFloor = bhkInput.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkFloor() {
        if (flooringInput.getText().toString().trim().length() > 0) {
            SellerAd.getInstance().adsFloor = flooringInput.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkAddress() {
        if (addressInput.getText().toString().length() > 0) {
            SellerAd.getInstance().adsPropertyAddress = addressInput.getText().toString().trim();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkLocation() {
        if (cityInput.getText().toString().length() > 0) {
            SellerAd.getInstance().adsLocation = cityInput.getText().toString().trim();
            return true;
        } else {
            return false;
        }
    }

    private boolean checkDoor() {
        if (doorNumberInput.getText().toString().length() > 0) {
            SellerAd.getInstance().adsDoorNo = doorNumberInput.getText().toString().trim();
            return true;
        } else {
            return false;
        }
    }

    private boolean roi() {
        if (roiInput.getText().toString().trim().length() > 0) {
            SellerAd.getInstance().adsRoi = roiInput.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean roiIncrement() {
        if (incrementCustomInput.getText().toString().trim().length() > 0) {
            SellerAd.getInstance().adsRoiIncrementPeriod = incrementCustomInput.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean roiIncrementalValue() {
        if (rentalIncrementalInput.getText().toString().trim().length() > 0) {
            SellerAd.getInstance().roiIncrementalValue = rentalIncrementalInput.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void onRadioButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.sell_btn:
                Log.d("SELL", "SELL");
                SellerAd.getInstance().adsSellOrRent = getString(R.string.SELL);
                farmLand.setVisibility(View.VISIBLE);
                rentalIncomeButton.setVisibility(View.VISIBLE);
                pgRentButton.setVisibility(View.VISIBLE);
                roiLayout.setVisibility(View.GONE);
                rentalIncrementalLayout.setVisibility(View.GONE);
                advanceDeposit.setVisibility(View.GONE);
                petsLayout.setVisibility(View.GONE);
                budgetInput.setHint(getString(R.string.ads_price_header));
                break;
            case R.id.rent_btn:
                SellerAd.getInstance().adsSellOrRent = getString(R.string.RENT);
                farmLand.setVisibility(View.VISIBLE);
                rentalIncomeButton.setVisibility(View.GONE);
                pgRentButton.setVisibility(View.VISIBLE);
                roiLayout.setVisibility(View.GONE);
                rentalIncrementalLayout.setVisibility(View.GONE);
                advanceDeposit.setVisibility(View.GONE);
                petsLayout.setVisibility(View.GONE);
                budgetInput.setHint(getString(R.string.rent));
                adsBudgetHeader.setText(getString(R.string.rent));
                break;
            case R.id.resi_radio_btn:
                resiSub.setVisibility(View.VISIBLE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                rentalIncrementalLayout.setVisibility(View.GONE);
                rentalStart.setVisibility(View.GONE);
                rentalEnd.setVisibility(View.GONE);
                advanceDeposit.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                indusIndependentGroup.setVisibility(View.GONE);
                tenantPreferances.setVisibility(View.GONE);
                vegNonVegLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.GONE);
                isRentalIncome = true;
                break;
            case R.id.comm_radio_btn:
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.VISIBLE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                rentalIncrementalLayout.setVisibility(View.GONE);
                rentalStart.setVisibility(View.GONE);
                rentalEnd.setVisibility(View.GONE);
                advanceDeposit.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                indusIndependentGroup.setVisibility(View.GONE);
                tenantPreferances.setVisibility(View.GONE);
                noOfHouse.setVisibility(View.GONE);
                totalFloors.setVisibility(View.GONE);
                vegNonVegLayout.setVisibility(View.GONE);
                propertySizeLayout.setVisibility(View.GONE);
                isRentalIncome = true;
                break;
            case R.id.indus_radio_btn:
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.VISIBLE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                rentalIncrementalLayout.setVisibility(View.GONE);
                rentalStart.setVisibility(View.GONE);
                rentalEnd.setVisibility(View.GONE);
                advanceDeposit.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                indusIndependentGroup.setVisibility(View.GONE);
                tenantPreferances.setVisibility(View.GONE);
                noOfHouse.setVisibility(View.GONE);
                totalFloors.setVisibility(View.GONE);
                vegNonVegLayout.setVisibility(View.GONE);
                propertySizeLayout.setVisibility(View.GONE);
                isRentalIncome = true;
                break;
            case R.id.indus_floorspace:
                SellerAd.getInstance().adsPropertyType = getString(R.string.industrial_floorspace);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.VISIBLE);
                isBuiltUpVisible = true;
                isLandAreaVisible = false;
                isRentalIncome = true;
                break;
            case R.id.rental_indus_floorspace:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_industrial_floorspace);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.VISIBLE);
                isBuiltUpVisible = true;
                isLandAreaVisible = false;
                break;
            case R.id.indus_independent:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                indusIndependentGroup.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                isBuiltUpVisible = true;
                isLandAreaVisible = true;
                break;
            case R.id.rental_indus_independent:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                rentalIndusIndependentGroup.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                isBuiltUpVisible = true;
                isLandAreaVisible = true;
                break;
            case R.id.ins_radio_btn:
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.VISIBLE);
                indusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                rentalIncrementalLayout.setVisibility(View.GONE);
                rentalStart.setVisibility(View.GONE);
                rentalEnd.setVisibility(View.GONE);
                advanceDeposit.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                tenantPreferances.setVisibility(View.GONE);
                noOfHouse.setVisibility(View.GONE);
                totalFloors.setVisibility(View.GONE);
                vegNonVegLayout.setVisibility(View.GONE);
                isBuiltUpVisible = true;
                isLandAreaVisible = true;
                isRentalIncome = true;
                break;
            case R.id.ins_building:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
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
                rentalIncrementalLayout.setVisibility(View.GONE);
                rentalStart.setVisibility(View.GONE);
                rentalEnd.setVisibility(View.GONE);
                advanceDeposit.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                rentalIncomeType.setVisibility(View.GONE);
                pgRentSub.setVisibility(View.VISIBLE);
                vegNonVegLayout.setVisibility(View.GONE);
                isRentalIncome = true;
                break;
            case R.id.resi_building:
                resiBuild.setVisibility(View.VISIBLE);
                commBuild.setVisibility(View.GONE);
                indusBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.resi_land:
                SellerAd.getInstance().adsPropertyType = getString(R.string.residential_land);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                noOfHouse.setVisibility(View.GONE);
                totalFloors.setVisibility(View.GONE);
                isBuiltUpVisible = false;
                isLandAreaVisible = true;
                break;
            case R.id.resi_independent:
                SellerAd.getInstance().adsPropertyType = getString(R.string.residential_independent);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.VISIBLE);
                noOfHouse.setVisibility(View.GONE);
                totalFloors.setVisibility(View.GONE);
                if (SellerAd.getInstance().adsSellOrRent.equals(getString(R.string.SELL))) {
                    petsLayout.setVisibility(View.GONE);
                } else if (SellerAd.getInstance().adsSellOrRent.equals(getString(R.string.RENT))) {
                    petsLayout.setVisibility(View.VISIBLE);
                }
                isBuiltUpVisible = true;
                isLandAreaVisible = true;
                break;
            case R.id.rental_resi_independent:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_residential_independent);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.VISIBLE);
                isBuiltUpVisible = true;
                isLandAreaVisible = true;
                break;
            case R.id.resi_apartments:
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.VISIBLE);
                if (v.isActivated()) {
                    petsLayout.setVisibility(View.VISIBLE);
                }
                noOfHouse.setVisibility(View.VISIBLE);
                totalFloors.setVisibility(View.VISIBLE);
                isBuiltUpVisible = true;
                isLandAreaVisible = false;
                break;
            case R.id.rental_resi_apartments:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_residential_apartments);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.VISIBLE);
                petsLayout.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.VISIBLE);
                noOfHouse.setVisibility(View.VISIBLE);
                totalFloors.setVisibility(View.VISIBLE);
                isBuiltUpVisible = true;
                isLandAreaVisible = false;
                break;
            case R.id.comm_building:
                resiBuild.setVisibility(View.GONE);
                commBuild.setVisibility(View.VISIBLE);
                indusBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.GONE);
                flooringLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.GONE);
                break;
            case R.id.comm_land:
                SellerAd.getInstance().adsPropertyType = getString(R.string.commercial_land);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                isBuiltUpVisible = false;
                isLandAreaVisible = true;
                break;
            case R.id.comm_independent:
                SellerAd.getInstance().adsPropertyType = getString(R.string.commercial_independent);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.GONE);
                isBuiltUpVisible = true;
                isLandAreaVisible = true;
                break;
            case R.id.rental_comm_independent:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_commercial_independent);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.GONE);
                isBuiltUpVisible = true;
                isLandAreaVisible = true;
                break;
            case R.id.comm_floorspace:
                SellerAd.getInstance().adsPropertyType = getString(R.string.commercial_floorspace);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                flooringLayout.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.GONE);
                isBuiltUpVisible = true;
                isLandAreaVisible = false;
                break;
            case R.id.rental_comm_floorspace:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_commercial_floorspace);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                landAreaLayout.setVisibility(View.GONE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.GONE);
                isBuiltUpVisible = true;
                isLandAreaVisible = false;
                break;
            case R.id.indus_land:
                SellerAd.getInstance().adsPropertyType = getString(R.string.industrial_land);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                flooringLayout.setVisibility(View.GONE);
                isBuiltUpVisible = false;
                isLandAreaVisible = true;
                break;
            case R.id.indus_building:
                resiBuild.setVisibility(View.GONE);
                commBuild.setVisibility(View.GONE);
                indusBuild.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.VISIBLE);
                indusIndependentGroup.setVisibility(View.GONE);
                break;
            case R.id.comm_showroom:
                SellerAd.getInstance().adsPropertyType = getString(R.string.showroom);
                propertySizeLayout.setVisibility(View.VISIBLE);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                showRoomLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.rental_comm_showroom:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_showroom);
                propertySizeLayout.setVisibility(View.VISIBLE);
                builtUpAreaLayout.setVisibility(View.VISIBLE);
                showRoomLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.new_btn:
                SellerAd.getInstance().adsIsNew = getString(R.string.new_text);
                break;
            case R.id.resale_btn:
                SellerAd.getInstance().adsIsNew = getString(R.string.resale_text);
                break;
            case R.id.north_btn:
                SellerAd.getInstance().adsFacing = getString(R.string.north_text);
                break;
            case R.id.south_btn:
                SellerAd.getInstance().adsFacing = getString(R.string.south_text);
                break;
            case R.id.west_btn:
                SellerAd.getInstance().adsFacing = getString(R.string.west_text);
                break;
            case R.id.east_btn:
                SellerAd.getInstance().adsFacing = getString(R.string.east_text);
                break;
            case R.id.furnished_btn:
                SellerAd.getInstance().adsFurnished = getString(R.string.furnished);
                break;
            case R.id.un_furnished_btn:
                SellerAd.getInstance().adsFurnished = getString(R.string.un_furnished);
                break;
            case R.id.semi_furnished_btn:
                SellerAd.getInstance().adsFurnished = getString(R.string.semi_furnished);
                break;
            case R.id.negotiable_btn:
                SellerAd.getInstance().adsBudgetNegotiable = getString(R.string.negotiable);
                Log.d("TEST", "REACHED");
                break;
            case R.id.non_negotiable_btn:
                SellerAd.getInstance().adsBudgetNegotiable = getString(R.string.non_negotiable);
                Log.d("TEST", "REACH00ED");
                break;
            case R.id.ads_rental_income:
                bhkLayout.setVisibility(View.GONE);
                builtUpAreaLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.GONE);
                propertySizeLayout.setVisibility(View.GONE);
                roiLayout.setVisibility(View.VISIBLE);
                rentalIncomeType.setVisibility(View.VISIBLE);
                roiLayout.setVisibility(View.VISIBLE);
                rentalIncrementalLayout.setVisibility(View.VISIBLE);
                rentalStart.setVisibility(View.VISIBLE);
                rentalEnd.setVisibility(View.VISIBLE);
                advanceDeposit.setVisibility(View.VISIBLE);
                budgetInput.setHint(getString(R.string.rent));
                isRentalIncome = true;
                break;
            case R.id.rental_resi_radio_btn:
                rentalResiSub.setVisibility(View.VISIBLE);
                rentalCommSub.setVisibility(View.GONE);
                rentalInsSub.setVisibility(View.GONE);
                rentalIndusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_comm_radio_btn:
                rentalResiSub.setVisibility(View.GONE);
                rentalCommSub.setVisibility(View.VISIBLE);
                rentalInsSub.setVisibility(View.GONE);
                rentalIndusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                tenantPreferances.setVisibility(View.GONE);
                vegNonVegLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_indus_radio_btn:
                rentalResiSub.setVisibility(View.GONE);
                rentalCommSub.setVisibility(View.GONE);
                rentalInsSub.setVisibility(View.GONE);
                rentalIndusSub.setVisibility(View.VISIBLE);
                pgRentLayout.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.GONE);
                tenantPreferances.setVisibility(View.GONE);
                vegNonVegLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_ins_radio_btn:
                rentalResiSub.setVisibility(View.GONE);
                rentalCommSub.setVisibility(View.GONE);
                rentalInsSub.setVisibility(View.VISIBLE);
                rentalIndusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.GONE);
                tenantPreferances.setVisibility(View.GONE);
                vegNonVegLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_resi_building:
                rentalResiBuild.setVisibility(View.VISIBLE);
                rentalCommBuild.setVisibility(View.GONE);
                rentalIndusBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.VISIBLE);
                vegNonVegLayout.setVisibility(View.VISIBLE);
                //tenantPreferances.setVisibility(View.VISIBLE);
                break;
            case R.id.rental_resi_land:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_residential_land);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                isBuiltUpVisible = false;
                isLandAreaVisible = true;
                break;
            case R.id.rental_comm_building:
                rentalResiBuild.setVisibility(View.GONE);
                rentalCommBuild.setVisibility(View.VISIBLE);
                rentalIndusBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.VISIBLE);
                tenantPreferances.setVisibility(View.GONE);
                break;
            case R.id.rental_comm_land:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_commercial_land);
                bhkLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.GONE);
                isBuiltUpVisible = false;
                isLandAreaVisible = true;
                break;
            case R.id.rental_indus_land:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_industrial_land);
                bhkLayout.setVisibility(View.GONE);
                landAreaLayout.setVisibility(View.VISIBLE);
                propertySizeLayout.setVisibility(View.VISIBLE);
                furnishedLayout.setVisibility(View.GONE);
                isBuiltUpVisible = false;
                isLandAreaVisible = true;
                break;
            case R.id.rental_indus_building:
                rentalResiBuild.setVisibility(View.GONE);
                rentalCommBuild.setVisibility(View.GONE);
                rentalIndusBuild.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.ads_farm_land:
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                rentalFarmLandSub.setVisibility(View.GONE);
                rentalPgServiceSub.setVisibility(View.GONE);
                farmLandGroup.setVisibility(View.VISIBLE);
                advanceDeposit.setVisibility(View.GONE);
                roiLayout.setVisibility(View.GONE);
                isRentalIncome = true;
                break;
            case R.id.farm_land_land:
                SellerAd.getInstance().adsPropertyType = getString(R.string.farm_land_land);
                break;
            case R.id.farm_land_building:
                SellerAd.getInstance().adsPropertyType = getString(R.string.farm_land_building);
                break;
            case R.id.rental_ads_farm_land:
                rentalResiBuild.setVisibility(View.GONE);
                rentalCommBuild.setVisibility(View.GONE);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.GONE);
                rentalFarmLandSub.setVisibility(View.VISIBLE);
                rentalPgServiceSub.setVisibility(View.GONE);
                break;
            case R.id.rental_pg_rent:
                rentalResiBuild.setVisibility(View.GONE);
                rentalCommBuild.setVisibility(View.GONE);
                rentalIndusBuild.setVisibility(View.VISIBLE);
                bhkLayout.setVisibility(View.GONE);
                furnishedLayout.setVisibility(View.VISIBLE);
                rentalFarmLandSub.setVisibility(View.GONE);
                rentalPgServiceSub.setVisibility(View.VISIBLE);
                rentalFarmLandSub.setVisibility(View.GONE);
                vegNonVegLayout.setVisibility(View.GONE);
                break;
            case R.id.rental_pg_independent:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_pg_independent);
                isBuiltUpVisible = true;
                isLandAreaVisible = true;
                break;
            case R.id.rental_pg_apartments:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_pg_apartments);
                isBuiltUpVisible = true;
                isLandAreaVisible = false;
                break;
            case R.id.rental_farm_land:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_farm_land);
                break;
            case R.id.rental_farm_building:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_farm_building);
                break;
            case R.id.ins_school:
                SellerAd.getInstance().adsPropertyType = getString(R.string.school);
                break;
            case R.id.ins_hospital:
                SellerAd.getInstance().adsPropertyType = getString(R.string.hospital);
                break;
            case R.id.ins_college:
                SellerAd.getInstance().adsPropertyType = getString(R.string.college);
                break;
            case R.id.ins_land:
                builtUpAreaLayout.setVisibility(View.GONE);
                isBuiltUpVisible = false;
                isLandAreaVisible = true;
                break;
            case R.id.rental_ins_college:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_college);
                break;
            case R.id.rental_ins_hospital:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_hospital);
                break;
            case R.id.rental_ins_school:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_college);
                break;
            case R.id.rental_ins_land:
                isBuiltUpVisible = false;
                isLandAreaVisible = true;
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_institutional_land);
                break;
            case R.id.rental_ads_indus_ware_house:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_warehouse);
                break;
            case R.id.rental_ads_indus_cold_storage:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_cold_storage);
                break;
            case R.id.rental_ads_indus_factory:
                SellerAd.getInstance().adsPropertyType = getString(R.string.rental_factory);
                break;
            case R.id.increment_custom:
                incrementCustomInput.setVisibility(View.VISIBLE);
                break;
            case R.id.no_increment:
                SellerAd.getInstance().adsRoiIncrementPeriod = "0";
                incrementCustomInput.setVisibility(View.GONE);
                break;
            case R.id.yearly_once:
                SellerAd.getInstance().adsRoiIncrementPeriod = "1";
                incrementCustomInput.setVisibility(View.GONE);
                break;
            case R.id.two_years_btn:
                SellerAd.getInstance().adsRoiIncrementPeriod = "2";
                incrementCustomInput.setVisibility(View.GONE);
                break;
            case R.id.three_years_btn:
                SellerAd.getInstance().adsRoiIncrementPeriod = "3";
                incrementCustomInput.setVisibility(View.GONE);
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

    private String setData(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            return getString(R.string.yes);
        } else {
            return getString(R.string.no);
        }
    }

    private void setFacilityData(CheckBox v, String data){
        if(v.isChecked()){
            SellerAd.getInstance().facilitiesList.add(data);
        }else{
            SellerAd.getInstance().facilitiesList.remove(data);
        }
    }

    private void setApprovalData(CheckBox v,String data){
        if(v.isChecked()){
            SellerAd.getInstance().approvalList.add(data);
        }else{
            SellerAd.getInstance().approvalList.remove(data);
        }
    }

    public void onCheckBoxClicked(View v) {
        switch (v.getId()) {
            case R.id.covered_parking_check:
                SellerAd.getInstance().adsCovCarParking = setData((CheckBox) v);
                if (((CheckBox) v).isChecked()) {
                    covPicker.setVisibility(View.VISIBLE);
                } else {
                    covPicker.setVisibility(View.GONE);
                }
                break;
            case R.id.un_cov_car_park:
                SellerAd.getInstance().adsUnCovParking = setData((CheckBox) v);
                if (((CheckBox) v).isChecked()) {
                    unCovPicker.setVisibility(View.VISIBLE);
                } else {
                    unCovPicker.setVisibility(View.GONE);
                }
                break;
            case R.id.ads_ground_water_check:
                setFacilityData((CheckBox)v,getString(R.string.ground_water));
                break;
            case R.id.ads_corp_water_check:
                setFacilityData((CheckBox)v,getString(R.string.corp_water));
                break;
            case R.id.drainage_check:
                setFacilityData((CheckBox)v,getString(R.string.drainage_connection));
                break;
            case R.id.gym_check:
                setFacilityData((CheckBox)v,getString(R.string.gym));
                break;
            case R.id.power_check:
                setFacilityData((CheckBox)v,getString(R.string.power_backup));
                break;
            case R.id.security_check:
                setFacilityData((CheckBox)v,getString(R.string.security_guard));
                break;
            case R.id.lift_check:
                setFacilityData((CheckBox)v,getString(R.string.lift));
                break;
            case R.id.swimming_check:
                setFacilityData((CheckBox)v,getString(R.string.swimming_pool));
                break;
            case R.id.cafetria_check:
                setFacilityData((CheckBox)v,getString(R.string.cafeteria));
                break;
            case R.id.garden_check:
                setFacilityData((CheckBox)v,getString(R.string.garden));
                break;
            case R.id.water_check:
                setFacilityData((CheckBox)v,getString(R.string.water));
                break;
            case R.id.play_area:
                setFacilityData((CheckBox)v,getString(R.string.play_area));
                break;
            case R.id.ads_cmda_btn:
                setApprovalData((CheckBox)v,getString(R.string.cdma_text));
                break;
            case R.id.ads_dtcp_btn:
                setApprovalData((CheckBox)v,getString(R.string.dtcp_text));
                break;
            case R.id.ads_corp:
                setApprovalData((CheckBox)v,getString(R.string.corporation));
                break;
            case R.id.ads_panchayat:
                setApprovalData((CheckBox)v,getString(R.string.panchayat));
                break;
            case R.id.ads_commercial:
                setApprovalData((CheckBox)v,getString(R.string.commercial));
                break;
            case R.id.ads_industrial:
                setApprovalData((CheckBox)v,getString(R.string.industrial));
                break;
            case R.id.ads_rera_btn:
                setApprovalData((CheckBox)v,getString(R.string.rera_text));
                break;
            case R.id.veg_check:
                SellerAd.getInstance().vegNonVeg = getString(R.string.veg);
                break;
            case R.id.non_veg_check:
                SellerAd.getInstance().vegNonVeg = getString(R.string.non_veg);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQ && resultCode == RESULT_OK && data != null) {
            propertyPic.setImageURI(data.getData());
        }
        if (requestCode == LOCATION_REQUEST && resultCode == RESULT_OK) {
            Place place = PlaceAutocomplete.getPlace(AdsActivity.this, data);
            cityInput.setText(place.getName().toString());
        }
    }

}
