package com.nyxwolves.wannabuy.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.Interfaces.ImageRecieved;
import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdsDetailActivity extends AppCompatActivity implements OnMapReadyCallback, CallbackInterface, ImageRecieved {

    TextView  bhkText, priceText, locationText, ageText, doorText, streetText, areaText, districtText, stateText;
    TextView adUserId, adDate, landArea, builtUpArea, furnishedText;
    TextView propertyType, facilitesText, approvalText, floorText;
    TextView covParking, unCovParking, isPetsAllowed, vegNonVeg;
    LinearLayout rentalLayoutOne, rentalLayoutTwo, rentalLayoutThree;
    LinearLayout pgLayoutOne, pgLayoutTwo, pgLayoutThree;
    TextView boysHostelPrice, girlsHostelPrice, workMenHostelPrice, workWomenHostelPrice, corpGuestPrice;
    TextView rentStart, rentEnd, advanceDeposit, roi, roiIncrement, roiIncrementPeriod;
    TextView rentPerMonth, withFood, noOfRooms, personPerRoom;
    ImageView adImage;
    Button emailBtn, callBtn,chatBtn;

    double latitude, longitude;
    int PERMISSION_CALL = 1200;
    String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_detail);

        //get the  ad id
        Intent intent = getIntent();
        String adId = intent.getStringExtra(getString(R.string.AD_ID));

        //ui elements
        adImage = findViewById(R.id.ad_image_header);
        doorText = findViewById(R.id.door_text);
        areaText = findViewById(R.id.area_text);
        streetText = findViewById(R.id.street_text);
        districtText = findViewById(R.id.district_text);
        stateText = findViewById(R.id.state_text);
        bhkText = findViewById(R.id.bhk_text);
        priceText = findViewById(R.id.price_text);
        adUserId = findViewById(R.id.ad_user_id);
        adDate = findViewById(R.id.ad_posted_date);
        locationText = findViewById(R.id.location_text);
        ageText = findViewById(R.id.age_text);
        landArea = findViewById(R.id.land_size_text);
        builtUpArea = findViewById(R.id.built_up_size_text);
        furnishedText = findViewById(R.id.furnished_text);
        propertyType = findViewById(R.id.property_type_text);
        facilitesText = findViewById(R.id.facilities_text);
        approvalText = findViewById(R.id.approval_text);
        floorText = findViewById(R.id.floor_text);
        covParking = findViewById(R.id.car_parking_cov);
        unCovParking = findViewById(R.id.car_parking_un_cov);
        isPetsAllowed = findViewById(R.id.pets_text);
        rentalLayoutOne = findViewById(R.id.rental_layout_one);
        rentalLayoutTwo = findViewById(R.id.rental_layout_two);
        rentalLayoutThree = findViewById(R.id.rental_layout_three);
        rentStart = findViewById(R.id.rent_start_text);
        rentEnd = findViewById(R.id.rent_end_text);
        advanceDeposit = findViewById(R.id.advance_deposit_text);
        vegNonVeg = findViewById(R.id.veg_non_veg_text);
        roi = findViewById(R.id.roi_text);
        roiIncrement = findViewById(R.id.roi_increment_value);
        roiIncrementPeriod = findViewById(R.id.roi_increment_period);

        pgLayoutOne = findViewById(R.id.pg_layout_one);
        pgLayoutTwo = findViewById(R.id.pg_layout_two);
        pgLayoutThree = findViewById(R.id.pg_layout_three);
        rentPerMonth = findViewById(R.id.rent_per_month_text);
        noOfRooms = findViewById(R.id.pg_rooms);
        personPerRoom = findViewById(R.id.pg_person);
        withFood = findViewById(R.id.with_food_text);
        boysHostelPrice = findViewById(R.id.boys_hostel_price_text);
        girlsHostelPrice = findViewById(R.id.girls_hostel_price_text);
        workMenHostelPrice = findViewById(R.id.work_men_hostel_price_text);
        workWomenHostelPrice = findViewById(R.id.work_women_hostel_price_text);
        corpGuestPrice = findViewById(R.id.corp_price_text);

        callBtn = findViewById(R.id.call_btn);
        emailBtn = findViewById(R.id.email_btn);
        chatBtn = findViewById(R.id.chat_btn);

        //api call to recieve complete info on the ad
        AdHelper helper = new AdHelper(this);
        //call back to get the result
        CallbackInterface callback = this;
        helper.readAd(adId, callback);

        //api call to get images for ad
        AdHelper imageHelper = new AdHelper(this);
        imageHelper.getAdImage(adId, this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(sydney));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f));
    }

    //callback from the api
    @Override
    public void setData(JSONObject data) {
        //display data from json
        try {

            //map fragment setup
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(AdsDetailActivity.this);

            //get data from JSON object
            doorText.setText(data.getString("DOOR_NO"));
            areaText.setText(data.getString("PROPERTY_AREA"));
            streetText.setText(data.getString("PROPERTY_STREET"));
            districtText.setText(data.getString("PROPERTY_DISTRICT"));
            stateText.setText(data.getString("PROPERTY_STATE"));
            bhkText.setText(data.getString("BHK"));
            priceText.setText(getString(R.string.rupee_symbol)+data.getString("BUDGET"));
            adUserId.setText(data.getString("USER_ID"));
            adDate.setText(data.getString("CREATED_AT").substring(0, 10));// to eliminate the time stamp from  the server
            ageText.setText(data.getString("AGE"));
            landArea.setText(data.getString("LAND_AREA"));
            builtUpArea.setText(data.getString("BUILT_UP_AREA"));
            furnishedText.setText(data.getString("FURNISHED"));
            String propertyTypeText = data.getString("PROPERTY_TYPE");
            propertyType.setText(propertyTypeText);
            floorText.setText(data.getString("FLOOR"));

            latitude = Double.valueOf(data.getString("LATITUDE"));
            longitude = Double.valueOf(data.getString("LONGITUDE"));

            //property type
            if (propertyTypeText.equals(getString(R.string.residential_land)) ||
                    propertyTypeText.equals(getString(R.string.commercial_land)) ||
                    propertyTypeText.equals(getString(R.string.industrial_land)) ||
                    propertyTypeText.equals(getString(R.string.institutional_land))) {
                bhkText.setText("-");

            }


            //facilites array
            JSONArray facilities = data.getJSONArray("FACILITIES");
            String facilityString = new String();
            for (int i = 0; i < facilities.length(); i++) {
                facilityString = facilityString + "\n" + facilities.get(i).toString();
            }
            facilitesText.setText(facilityString);


            //approval array
            JSONArray approvalList = data.getJSONArray("APPROVAL");
            String approvalString = new String();
            for (int i = 0; i < approvalList.length(); i++) {
                approvalString = approvalString + "\n" + approvalList.get(i).toString();
            }
            approvalText.setText(approvalString);


            //car parking
            if (data.getString("COV_CAR_PARKING").equals(getString(R.string.yes)) ||
                    data.getString("UN_COV_CAR_PARKING").equals(getString(R.string.yes))) {

                String covCar = "Covered Car Parking: " + data.getString("COV_CAR_PARKING_NUM");
                String unCovCar = "Uncovered Car Parking: " + data.getString("UN_COV_CAR_PARKING_NUM");
                covParking.setText(covCar);
                unCovParking.setText(unCovCar);
            } else if (data.getString("UN_COV_CAR_PARKING").equals(getString(R.string.yes))) {

                String unCovCar = "Uncovered Car Parking: " + data.getString("UN_COV_CAR_PARKING_NUM");
                unCovParking.setText(unCovCar);
            } else if (data.getString("COV_CAR_PARKING").equals(getString(R.string.yes))) {

                String covCar = "Covered Car Parking: " + data.getString("COV_CAR_PARKING_NUM");
                covParking.setText(covCar);
            }

            //pets allowed
            if (propertyTypeText.equals(getString(R.string.residential_apartments)) ||
                    propertyTypeText.equals(getString(R.string.rental_residential_apartments))) {
                isPetsAllowed.setVisibility(View.VISIBLE);
                if (data.getString("PETS_ALLOWED").equals(getString(R.string.yes).toUpperCase())) {
                    isPetsAllowed.setText("Pets Allowed");
                } else {
                    isPetsAllowed.setText("Pets Not Allowed");
                }
            }

            //veg non-veg
            String displayText = "";
            if (data.getString("VEG").equals(getString(R.string.veg))) {
                displayText = data.getString("VEG");
            }
            if (data.getString("NON_VEG").equals(getString(R.string.non_veg))) {
                displayText = displayText + "\n" + data.getString("NON_VEG");
            }
            vegNonVeg.setText(displayText);

            //rental property check
            if (propertyTypeText.equals(getString(R.string.rental_residential_land)) ||
                    propertyTypeText.equals(getString(R.string.rental_residential_apartments)) ||
                    propertyTypeText.equals(getString(R.string.rental_residential_independent)) ||
                    propertyTypeText.equals(getString(R.string.rental_commercial_land)) ||
                    propertyTypeText.equals(getString(R.string.rental_commercial_floorspace)) ||
                    propertyTypeText.equals(getString(R.string.rental_commercial_independent)) ||
                    propertyTypeText.equals(getString(R.string.rental_industrial_land)) ||
                    propertyTypeText.equals(getString(R.string.rental_industrial_floorspace)) ||
                    propertyTypeText.equals(getString(R.string.rental_industrial_independent)) ||
                    propertyTypeText.equals(getString(R.string.rental_institutional_land)) ||
                    propertyTypeText.equals(getString(R.string.rental_pg_apartments)) ||
                    propertyTypeText.equals(getString(R.string.rental_pg_independent)) ||
                    propertyTypeText.equals(getString(R.string.rental_farm_land)) ||
                    propertyTypeText.equals(getString(R.string.rental_farm_building))) {

                rentalLayoutOne.setVisibility(View.VISIBLE);
                rentalLayoutTwo.setVisibility(View.VISIBLE);
                rentalLayoutThree.setVisibility(View.VISIBLE);

                advanceDeposit.setText(data.getString("ADVANCE_DEPOSIT"));
                roi.setText(data.getString("ROI"));
                roiIncrement.setText(data.getString("ROI_INCREMENT"));
                roiIncrementPeriod.setText(data.getString("ROI_INCREMENT_PERIOD"));
                rentStart.setText(data.getString("RENT_START"));
                rentEnd.setText(data.getString("RENT_END"));
            }

            //pg service apartments fields
            if (propertyTypeText.equals(getString(R.string.pg_rent_service_aparments)) ||
                    propertyTypeText.equals(getString(R.string.pg_rent_independent)) ||
                    propertyTypeText.equals(getString(R.string.rental_pg_independent)) ||
                    propertyTypeText.equals(getString(R.string.rental_pg_apartments))) {

                pgLayoutOne.setVisibility(View.VISIBLE);
                pgLayoutTwo.setVisibility(View.VISIBLE);
                pgLayoutThree.setVisibility(View.VISIBLE);

                withFood.setText(data.getString("PG_WITH_FOOD"));
                noOfRooms.setText(data.getString("PG_ROOMS"));
                personPerRoom.setText(data.getString("PG_PERSON_PER_ROOM"));
                boysHostelPrice.setText(data.getString("BOYS_HOSTEL_PRICE"));
                girlsHostelPrice.setText(data.getString("GIRLS_HOSTEL_PRICE"));
                workMenHostelPrice.setText(data.getString("WORKING_MEN_HOSTEL_PRICE"));
                workWomenHostelPrice.setText(data.getString("WORKING_WOMEN_HOSTEL_PRICE"));
                corpGuestPrice.setText(data.getString("CORP_GUEST_PRICE"));
            }

            //call btn
            phoneNum = data.getString("PHONE_NUMBER");
            callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if(ContextCompat.checkSelfPermission(AdsDetailActivity.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CALL);
                        }else{
                            makeCall();
                        }
                    } else {
                       makeCall();
                    }
                }
            });

            //email btn
            final String email = data.getString("USER_ID");
            emailBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL, email);
                    intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

            //chat button
            chatBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AdsDetailActivity.this,"Comming soon",Toast.LENGTH_SHORT).show();
                }
            });

        } catch (JSONException e) {
            Log.d("JSON EXCEPTION", e.toString());
        }
    }

    private void makeCall(){
        if(ContextCompat.checkSelfPermission(AdsDetailActivity.this, Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+phoneNum));
            startActivity(callIntent);
        }
    }
    @Override
    public void isSuccess(boolean isSuccess) {

    }

    @Override
    public void doesUserExits(boolean isExists) {

    }

    @Override
    public void dealerOrUser(boolean isDealer) {

    }

    @Override
    public void imageRecieved(JSONObject imageUrl) {
        String firstImageUrl = "";
        try {
            JSONArray urlList = imageUrl.getJSONArray("IMAGES");
            JSONObject firstUrl = urlList.getJSONObject(0);
            Log.d("FIRST_IMAGE", firstUrl.getString("URL"));
            firstImageUrl = "http://www.wannabuy.in/api/images/" + firstUrl.getString("URL");
        } catch (Exception e) {
        }

        Glide.with(AdsDetailActivity.this).load(firstImageUrl).into(adImage);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_CALL && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            makeCall();
        }
    }
}