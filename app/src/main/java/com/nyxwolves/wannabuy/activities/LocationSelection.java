package com.nyxwolves.wannabuy.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.database.Transaction;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class LocationSelection extends AppCompatActivity {

    PlaceAutocompleteFragment autocompleteFragment;
    AutocompleteFilter filter;
    Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selection);

        resultIntent = new Intent();

        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.auto_complete_fragment);

        if(getIntent().getAction().equals("CITY")){

            LatLng latLng = Requirements.getInstance().stateBoundary;
            if(latLng != null){
                autocompleteFragment.setBoundsBias(new LatLngBounds(latLng,latLng));
            }
        }else if(getIntent().getAction().equals("LOCATION")){

            LatLng latLng = Requirements.getInstance().cityBoundary;
            if(latLng != null){
                autocompleteFragment.setBoundsBias(new LatLngBounds(latLng,latLng));
            }
        }


        filter = new AutocompleteFilter.Builder()
                .setTypeFilter(Place.TYPE_COUNTRY)
                .setCountry("IN")
                .build();
        autocompleteFragment.setFilter(filter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.d("CITY",place.getName().toString());
                Requirements.getInstance().cityBoundary = place.getLatLng();
                resultIntent.putExtra("DATA",place.getName().toString());
                setResult(Activity.RESULT_OK,resultIntent);
                finish();
            }

            @Override
            public void onError(Status status) {
                Log.d("CITY","ERROR");
                setResult(Activity.RESULT_CANCELED,null);
                finish();
            }
        });
    }
}
