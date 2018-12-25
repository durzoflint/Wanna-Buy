package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class AreaLocality extends AppCompatActivity implements View.OnClickListener {

    Button nextBtn;
    EditText locationInput;
    GeoDataClient geoDataClient;
    TextView locationOne, locationTwo, locationThree, locationFour, locationFive;

    final int LOCATION_REQUEST = 1200;
    boolean isOneFull = false;
    boolean isTwoFull = false;
    boolean isThreeFull = false;
    boolean isFourFull = false;
    boolean isFiveFull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_locality);

        geoDataClient = Places.getGeoDataClient(this);

        locationOne = findViewById(R.id.location_one);
        locationOne.setOnClickListener(this);

        locationTwo = findViewById(R.id.location_two);
        locationTwo.setOnClickListener(this);

        locationThree = findViewById(R.id.location_three);
        locationThree.setOnClickListener(this);

        locationFour = findViewById(R.id.location_four);
        locationFour.setOnClickListener(this);

        locationFive = findViewById(R.id.location_five);
        locationFive.setOnClickListener(this);

        locationInput = findViewById(R.id.location_input);
        locationInput.setOnClickListener(this);

        nextBtn = findViewById(R.id.area_next_btn);
        nextBtn.setOnClickListener(this);
    }

    private boolean checkInput() {
        if(Requirements.getInstance().checkLocation()){
            return true;
        }else{
           return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.location_input:
                getLocationName();
                break;
            case R.id.location_five:
                locationFive.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationFive = "NONE";
                isFiveFull=false;
                break;
            case R.id.location_four:
                locationFour.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationFour = "NONE";
                isFourFull=false;
                break;
            case R.id.location_three:
                locationThree.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationThree = "NONE";
                isThreeFull=false;
                break;
            case R.id.location_two:
                locationTwo.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationTwo = "NONE";
                isTwoFull=false;
                break;
            case R.id.location_one:
                locationOne.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationOne = "NONE";
                isOneFull=false;
                break;
            case R.id.area_next_btn:
                if (checkInput()) {
                    startActivity(new Intent(AreaLocality.this, PropertyType.class));
                }
                break;
        }
    }

    private void getLocationName() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_REQUEST && resultCode == RESULT_OK) {
            Place places = PlaceAutocomplete.getPlace(this, data);
            showPlaces(places.getName().toString());
        }
    }

    private void showPlaces(String place) {
        if (isOneFull) {
            if (isTwoFull) {
                if (isThreeFull) {
                    if (isFourFull) {
                        if (isFiveFull) {
                            Toast.makeText(this, "Maximumu number of location selected", Toast.LENGTH_SHORT).show();
                        } else {
                            locationFive.setVisibility(View.VISIBLE);
                            locationFive.setBackground(getDrawable(R.drawable.selected_location));
                            Requirements.getInstance().locationFive = place;
                            locationFive.setText(place);
                            isFiveFull = true;
                        }
                    } else {
                        locationFour.setVisibility(View.VISIBLE);
                        Requirements.getInstance().locationFour = place;
                        locationFour.setBackground(getDrawable(R.drawable.selected_location));
                        locationFour.setText(place);
                        isFourFull = true;
                    }
                } else {
                    locationThree.setVisibility(View.VISIBLE);
                    Requirements.getInstance().locationThree = place;
                    locationThree.setBackground(getDrawable(R.drawable.selected_location));
                    locationThree.setText(place);
                    isThreeFull = true;
                }
            } else {
                locationTwo.setVisibility(View.VISIBLE);
                Requirements.getInstance().locationTwo = place;
                locationTwo.setBackground(getDrawable(R.drawable.selected_location));
                locationTwo.setText(place);
                isTwoFull = true;
            }
        } else {
            locationOne.setVisibility(View.VISIBLE);
            Requirements.getInstance().locationOne = place;
            locationOne.setBackground(getDrawable(R.drawable.selected_location));
            locationOne.setText(place);
            isOneFull = true;
        }
    }
}
