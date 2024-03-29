package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class BuyOrRent extends AppCompatActivity{

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_rent);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nextBtn = findViewById(R.id.buy_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Requirements.getInstance().buyorRent.equals("NOT_SET")) {
                    startActivity(new Intent(BuyOrRent.this, AreaLocality.class));
                }else{
                    Toast.makeText(BuyOrRent.this, "Select one", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.buy_radio_btn:
                Requirements.getInstance().buyorRent=getString(R.string.BUY);
                break;
            case R.id.rent_radio_btn:
                Requirements.getInstance().buyorRent=getString(R.string.RENT);
                break;
        }
    }
}
