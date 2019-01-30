package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.POJO.SellerAd;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.AdHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdsDetailActivity extends AppCompatActivity implements OnMapReadyCallback, CallbackInterface {

    TextView addressText,bhkText,priceText,locationText,ageText;
    TextView adUserId,adDate,landArea,builtUpArea,furnishedText;
    TextView propertyType,facilitesText,approvalText,floorText;
    TextView covParking,unCovParking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //get the  ad id
        Intent intent = getIntent();
        String adId = intent.getStringExtra(getString(R.string.AD_ID));

        //api call to recieve complete info on the ad
        AdHelper helper = new AdHelper(this);
        //call back to get the result
        CallbackInterface callback = this;
        helper.readAd(adId,callback);

        //ui elements
        addressText = findViewById(R.id.address_text);
        bhkText = findViewById(R.id.bhk_text);
        priceText = findViewById(R.id.price_text);
        adUserId = findViewById(R.id.ad_user_id);
        adDate = findViewById(R.id.ad_posted_date);
        locationText = findViewById(R.id.location_text);
        ageText = findViewById(R.id.bhk_text);
        landArea = findViewById(R.id.land_size_text);
        builtUpArea = findViewById(R.id.built_up_size_text);
        furnishedText = findViewById(R.id.furnished_text);
        propertyType = findViewById(R.id.property_type_text);
        facilitesText = findViewById(R.id.facilities_text);
        approvalText = findViewById(R.id.approval_text);
        floorText = findViewById(R.id.floor_text);
        covParking = findViewById(R.id.car_parking_cov);
        unCovParking = findViewById(R.id.car_parking_un_cov);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f));
    }

    //callback from the api
    @Override
    public void setData(JSONObject data) {

        try{
            addressText.setText(data.getString("PROPERTY_ADDRESS"));
            bhkText.setText(data.getString("BHK"));
            priceText.setText(data.getString("BUDGET"));
            adUserId.setText(data.getString("USER_ID"));
            adDate.setText(data.getString("CREATED_AT").substring(0,10));
            locationText.setText(data.getString("PROPERTY_LOCATION"));
            ageText.setText(data.getString("AGE"));
            landArea.setText(data.getString("LAND_AREA"));
            builtUpArea.setText(data.getString("BUILT_UP_AREA"));
            furnishedText.setText(data.getString("FURNISHED"));
            propertyType.setText(data.getString("PROPERTY_TYPE"));
            floorText.setText(data.getString("FLOOR"));

            //facilites array
            JSONArray facilities = data.getJSONArray("FACILITIES");
            String facilityString = new String();
            for(int i=0; i < facilities.length(); i++){
                facilityString = facilityString +"\n"+facilities.get(i).toString();
            }
            facilitesText.setText(facilityString);

            //approval array
            JSONArray approvalList = data.getJSONArray("APPROVAL");
            String approvalString = new String();
            for(int i=0; i < approvalList.length(); i++){
                approvalString = approvalString +"\n"+approvalList.get(i).toString();
            }
            approvalText.setText(approvalString);

            //car parking
            if(data.getString("COV_CAR_PARKING").equals("YES") ||
                    data.getString("UN_COV_CAR_PARKING").equals("YES")){

                String covCar = "Covered Car Parking: "+data.getString("COV_CAR_PARKING_NUM");
                String unCovCar = "Uncovered Car Parking: "+data.getString("UN_COV_CAR_PARKING_NUM");
                covParking.setText(covCar);
                unCovParking.setText(unCovCar);
            }else if(data.getString("UN_COV_CAR_PARKING").equals("YES")){

                String unCovCar = "Uncovered Car Parking: "+data.getString("UN_COV_CAR_PARKING_NUM");
                unCovParking.setText(unCovCar);
            }else if(data.getString("COV_CAR_PARKING").equals("YES")){
                
                String covCar = "Covered Car Parking: "+data.getString("COV_CAR_PARKING_NUM");
                covParking.setText(covCar);
            }

        }catch (JSONException e){
            Log.d("JSON EXCEPTION",e.toString());
        }


    }
}