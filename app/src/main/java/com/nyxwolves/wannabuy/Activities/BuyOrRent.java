package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class BuyOrRent extends AppCompatActivity implements View.OnClickListener{

    Button buyBtn,rentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_rent);

        buyBtn = findViewById(R.id.buy_btn);
        buyBtn.setOnClickListener(this);

        rentBtn = findViewById(R.id.rent_btn);
        rentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(BuyOrRent.this,AreaLocality.class);
        switch (v.getId()){
            case R.id.buy_btn:
                Requirements.getInstance().buyorRent="Buy";
                startActivity(i);
                break;
            case R.id.rent_btn:
                Requirements.getInstance().buyorRent="Rent";
                startActivity(i);
                break;
        }
    }
}
