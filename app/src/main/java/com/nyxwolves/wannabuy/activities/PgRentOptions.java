package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

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

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.girls_btn:
                if(((CheckBox)v).isChecked()){
                    Requirements.getInstance().pgRentGirls = getString(R.string.girls);
                }else{
                    Requirements.getInstance().pgRentGirls = getString(R.string.not_set_text);
                }
                break;
            case R.id.boys_btn:
                if(((CheckBox)v).isChecked()){
                    Requirements.getInstance().pgRentBoys = getString(R.string.boys);
                }else{
                    Requirements.getInstance().pgRentBoys = getString(R.string.not_set_text);
                }
                break;
            case R.id.family:
                if(((CheckBox)v).isChecked()){
                    Requirements.getInstance().pgRentFamily = getString(R.string.family);
                }else{
                    Requirements.getInstance().pgRentFamily = getString(R.string.not_set_text);
                }
                break;
            case R.id.short_stay:
                if(((CheckBox)v).isChecked()){
                    Requirements.getInstance().pgRentShortStay = getString(R.string.short_stay);
                }else{
                    Requirements.getInstance().pgRentShortStay = getString(R.string.not_set_text);
                }
                break;
        }
    }
}
