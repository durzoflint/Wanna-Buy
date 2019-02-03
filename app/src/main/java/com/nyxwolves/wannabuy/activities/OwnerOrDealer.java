package com.nyxwolves.wannabuy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nyxwolves.wannabuy.Interfaces.CallbackInterface;
import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.RestApiHelper.UserHelper;

import org.json.JSONObject;

public class OwnerOrDealer extends AppCompatActivity implements CallbackInterface {

    Button ownerBtn,dealerBtn,nextBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextInputEditText phoneInput;

    String ownerOrDealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_or_dealer);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);


        if(sharedPreferences.getString(getString(R.string.owner_dealer_flag),getString(R.string.NOT_SET)).equals(getString(R.string.SET))){
            Intent homeIntent = new Intent(OwnerOrDealer.this,HomeActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
        }

        ownerBtn = findViewById(R.id.owner_btn);
        dealerBtn = findViewById(R.id.dealer_btn);

        ownerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ownerOrDealer = getString(R.string.individual);
                dealerBtn.setBackground(getDrawable(R.drawable.blue_button));
                ownerBtn.setBackground(getDrawable(R.drawable.orange_button));
            }
        });

        dealerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownerOrDealer = getString(R.string.dealer);
                dealerBtn.setBackground(getDrawable(R.drawable.orange_button));
                ownerBtn.setBackground(getDrawable(R.drawable.blue_button));
            }
        });

        phoneInput = findViewById(R.id.phone_input);

        nextBtn = findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Long.parseLong(phoneInput.getText().toString().trim());

                    if(phoneInput.getText().toString().trim().length() == 10 ){
                        UserHelper userHelper = new UserHelper(OwnerOrDealer.this);
                        CallbackInterface callbackInterface = OwnerOrDealer.this;
                        userHelper.createUser(callbackInterface,ownerOrDealer,phoneInput.getText().toString());
                    }else{
                        Toast.makeText(OwnerOrDealer.this,"Invalid Phone number",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Log.d("NUM",""+e.toString());
                    Toast.makeText(OwnerOrDealer.this,"Invalid Phone number",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    public void isSuccess(boolean isSuccess) {
        if(isSuccess){
            editor = sharedPreferences.edit();
            editor.putString(getString(R.string.owner_dealer_flag),getString(R.string.SET));
            editor.apply();
            startActivity(new Intent(OwnerOrDealer.this,WelcomeScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }else{
            Toast.makeText(this,"Please Try Again",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setData(JSONObject data) {

    }

    @Override
    public void doesUserExits(boolean isExists) {

    }
}
