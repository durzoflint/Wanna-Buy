package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class LocationInputActivity extends AppCompatActivity implements View.OnClickListener{

    PlaceAutocompleteFragment locationFragment;
    AutocompleteFilter filter;
    TextView locationOne, locationTwo, locationThree, locationFour, locationFive;
    Button nextBtn;

    boolean isOneFull = false;
    boolean isTwoFull = false;
    boolean isThreeFull = false;
    boolean isFourFull = false;
    boolean isFiveFull = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_input);

        nextBtn = findViewById(R.id.location_next_btn);
        nextBtn.setOnClickListener(this);

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

        locationFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.location_input_frag);
        LatLng latLng = Requirements.getInstance().cityBoundary;
        locationFragment.setBoundsBias(new LatLngBounds(latLng,latLng));


        filter = new AutocompleteFilter.Builder()
                .setCountry("IN")
                .build();
        locationFragment.setFilter(filter);

        locationFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                showPlaces(place.getName().toString());

            }

            @Override
            public void onError(Status status) {
                Log.d("CITY","ERROR");
            }
        });

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location_next_btn:
                if (Requirements.getInstance().checkLocation()) {
                    startActivity(new Intent(LocationInputActivity.this, PropertyType.class));
                }else{
                    Toast.makeText(LocationInputActivity.this,"Check your inputs.",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.location_five:
                locationFive.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationFive = "NOT_SET";
                isFiveFull=false;
                break;
            case R.id.location_four:
                locationFour.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationFour = "NOT_SET";
                isFourFull=false;
                break;
            case R.id.location_three:
                locationThree.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationThree = "NOT_SET";
                isThreeFull=false;
                break;
            case R.id.location_two:
                locationTwo.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationTwo = "NOT_SET";
                isTwoFull=false;
                break;
            case R.id.location_one:
                locationOne.setVisibility(View.INVISIBLE);
                Requirements.getInstance().locationOne = "NOT_SET";
                isOneFull=false;
                break;
        }
    }
}
