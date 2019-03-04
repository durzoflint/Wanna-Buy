package com.nyxwolves.wannabuy.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class CityInputActivity extends AppCompatActivity {

    PlaceAutocompleteFragment cityFragment;
    AutocompleteFilter filter;
    Button nextBtn;

    boolean isCitySelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_input);

        nextBtn = findViewById(R.id.city_next_btn);

        cityFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.city_input_frag);
        LatLng latLng = Requirements.getInstance().stateBoundary;
        cityFragment.setBoundsBias(new LatLngBounds(latLng,latLng));


        filter = new AutocompleteFilter.Builder()
                .setTypeFilter(Place.TYPE_COUNTRY)
                .setCountry("IN")
                .build();
        cityFragment.setFilter(filter);

        cityFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                isCitySelected = true;
                cityFragment.setText(place.getName());
                Requirements.getInstance().city = place.getName().toString();
                Requirements.getInstance().cityBoundary = place.getLatLng();
            }

            @Override
            public void onError(Status status) {
                isCitySelected = false;
                Log.d("CITY","ERROR");
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCitySelected){
                    startActivity(new Intent(CityInputActivity.this, LocationInputActivity.class));
                }else{
                    Toast.makeText(CityInputActivity.this,"Please Select a city",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
