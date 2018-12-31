package com.nyxwolves.wannabuy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nyxwolves.wannabuy.R;

import java.security.acl.Owner;

public class OwnerOrDealer extends AppCompatActivity {

    Button ownerBtn,dealerBtn;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_or_dealer);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if(checkUserMode()){
            startActivity(new Intent(OwnerOrDealer.this,HomeActivity.class));
        }

        ownerBtn = findViewById(R.id.owner_btn);
        ownerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(getString(R.string.user_mode),getString(R.string.owner));
                startActivity(new Intent(OwnerOrDealer.this,HomeActivity.class));
            }
        });

        dealerBtn = findViewById(R.id.dealer_btn);
        dealerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(getString(R.string.user_mode),getString(R.string.dealer));
                startActivity(new Intent(OwnerOrDealer.this,HomeActivity.class));
            }
        });
    }

    private boolean checkUserMode(){
        if(sharedPreferences.getString(getString(R.string.user_mode),"NONE").equals(getString(R.string.owner))
                || sharedPreferences.getString(getString(R.string.user_mode),"NONE").equals(getString(R.string.dealer))){
            return true;
        }else{
            return false;
        }
    }

}
