package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class PgRentOptions extends AppCompatActivity {

    Button  nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg_rent_options);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        nextButton = findViewById(R.id.pg_rent_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PgRentOptions.this,Building.class));
            }
        });
    }

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.girls_btn:
                Requirements.getInstance().pgRentType = getString(R.string.girls);
                break;
            case R.id.boys_btn:
                Requirements.getInstance().pgRentType = getString(R.string.boys);
                break;
            case R.id.family:
                Requirements.getInstance().pgRentType = getString(R.string.family);
                break;
            case R.id.short_stay:
                Requirements.getInstance().pgRentType = getString(R.string.short_stay);
                break;
        }
    }
}
