package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
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

public class AdsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView selectedSize,maxSize;
    TextInputEditText cityInput,doorNumberInput,addressInput;
    SeekBar sizeSeekBar;
    Button picUploadBtn,paymentBtn;
    CircleImageView propertyPic;

    RadioGroup resiSub,commSub,insSub,indusSub;
    RadioGroup resiBuild,commBuild,insBuild,indusBuild;
    ConstraintLayout industrialGroup;
    FrameLayout pgRentLayout;


    final int IMAGE_REQ=1002;
    final int LOCATION_REQUEST = 1003;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        resiSub = findViewById(R.id.resi_sub);
        resiBuild = findViewById(R.id.resi_building_group);

        commSub = findViewById(R.id.comm_sub);
        commBuild = findViewById(R.id.comm_building_group);

        insSub = findViewById(R.id.ins_sub);

        indusSub = findViewById(R.id.indus_sub);
        indusBuild = findViewById(R.id.indus_building_group);

        pgRentLayout =findViewById(R.id.pg_rent_layout);

        cityInput = findViewById(R.id.ads_area_input);
        cityInput.setOnClickListener(this);

        doorNumberInput = findViewById(R.id.ads_door_input);
        addressInput = findViewById(R.id.ads_address_input);

        selectedSize = findViewById(R.id.ads_selected_size);
        maxSize = findViewById(R.id.max_size);

        sizeSeekBar = findViewById(R.id.ads_seekbar);


        propertyPic = findViewById(R.id.prop_pic);

        picUploadBtn = findViewById(R.id.upload_btn);
        picUploadBtn.setOnClickListener(this);

        paymentBtn = findViewById(R.id.payment_btn);
        paymentBtn.setOnClickListener(this);


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
                break;
            case R.id.rent_btn:
                SellerAd.getInstance().sellOrRent = getString(R.string.RENT);
                break;
            case R.id.resi_radio_btn:
                sizeSeekBar.setMax(10000);
                maxSize.setText("10000");
                resiSub.setVisibility(View.VISIBLE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.GONE);
                break;
            case R.id.comm_radio_btn:
                sizeSeekBar.setMax(100000);
                maxSize.setText("100000");
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.VISIBLE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.GONE);
                break;
            case R.id.indus_radio_btn:
                sizeSeekBar.setMax(100000);
                maxSize.setText("100000");
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.VISIBLE);
                break;
            case R.id.ins_radio_btn:
                sizeSeekBar.setMax(100000);
                maxSize.setText("100000");
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.VISIBLE);
                indusSub.setVisibility(View.GONE);
                break;
            case R.id.pg_rent:
                resiSub.setVisibility(View.GONE);
                commSub.setVisibility(View.GONE);
                insSub.setVisibility(View.GONE);
                indusSub.setVisibility(View.GONE);
                pgRentLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.resi_building:
                resiBuild.setVisibility(View.VISIBLE);
                commBuild.setVisibility(View.GONE);
                indusBuild.setVisibility(View.GONE);
                break;
            case R.id.comm_building:
                resiBuild.setVisibility(View.GONE);
                commBuild.setVisibility(View.VISIBLE);
                indusBuild.setVisibility(View.GONE);
                break;
            case R.id.indus_building:
                resiBuild.setVisibility(View.GONE);
                commBuild.setVisibility(View.GONE);
                indusBuild.setVisibility(View.VISIBLE);
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
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_REQ);
    }

    public void oncheckBoxClicked(View v){

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
