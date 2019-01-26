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
    private void setData(CheckBox v, String data){
        if(v.isChecked()){
            Requirements.getInstance().pgTypeList.add(data);
        }else{
            Requirements.getInstance().pgTypeList.remove(data);
        }
    }
    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.girls_btn:
                setData((CheckBox)v, getString(R.string.girls));
                break;
            case R.id.boys_btn:
               setData((CheckBox)v, getString(R.string.boys));
               break;
            case R.id.family:
                setData((CheckBox)v, getString(R.string.family));
                break;
            case R.id.short_stay:
                setData((CheckBox)v, getString(R.string.short_stay));
                break;
        }
    }
}
