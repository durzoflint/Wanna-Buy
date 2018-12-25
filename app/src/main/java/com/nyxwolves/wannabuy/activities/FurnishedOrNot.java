package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.nyxwolves.wannabuy.R;

public class FurnishedOrNot extends AppCompatActivity implements View.OnClickListener{

    RadioGroup industrialGroup,commAndResiGroup;
    Button continueBtn,backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furnished_or_not);

        continueBtn = findViewById(R.id.continue_btn);
        continueBtn.setOnClickListener(this);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);
        commAndResiGroup = findViewById(R.id.resi_comm_radio_group);
        showRadioGroup();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_btn:
                Intent i = new Intent(FurnishedOrNot.this,Bhk.class);
                i.putExtra(getString(R.string.PROPERTY_TYPE),"RESIDENTIAL");
                startActivity(i);
                break;
            case R.id.back_btn:
                super.onBackPressed();
                break;
        }
    }

    public void showRadioGroup(){
        commAndResiGroup.setVisibility(View.VISIBLE);
    }

    public void radioButtonClicked(View v){

    }
}
