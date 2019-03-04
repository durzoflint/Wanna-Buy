package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class StateInputActivity extends AppCompatActivity{

    Button nextBtn;
    PlaceAutocompleteFragment stateFragment;
    AutocompleteFilter filter;

    boolean isStateSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_input);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        stateFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.state_input_frag);
        stateFragment.setHint(getString(R.string.state_text));
        filter = new AutocompleteFilter.Builder()
                .setTypeFilter(Place.TYPE_COUNTRY)
                .setCountry("IN")
                .build();
        stateFragment.setFilter(filter);

        nextBtn = findViewById(R.id.area_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStateSelected){
                    startActivity(new Intent(StateInputActivity.this, CityInputActivity.class));
                }

            }
        });

        stateFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                isStateSelected = true;
                stateFragment.setText(place.getName().toString());
                Requirements.getInstance().state = place.getName().toString();
                Requirements.getInstance().stateBoundary = place.getLatLng();
            }

            @Override
            public void onError(Status status) {
                isStateSelected = false;
            }
        });
    }




}
