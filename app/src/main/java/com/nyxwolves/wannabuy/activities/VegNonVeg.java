package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class VegNonVeg extends AppCompatActivity {

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_non_veg);

        nextBtn = findViewById(R.id.veg_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VegNonVeg.this,RequirementName.class));
            }
        });
    }

    private String setData(CheckBox v){
        if(v.isChecked()){
            return getString(R.string.yes);
        }else{
            return getString(R.string.no);
        }
    }

    public void onCheckBoxClicked(View v){
        switch(v.getId()){
            case R.id.veg_check:
                Requirements.getInstance().isVeg = setData((CheckBox)v);
                break;
            case R.id.non_veg_check:
                Requirements.getInstance().isNonVeg = setData((CheckBox)v);
                break;
        }
    }
}
