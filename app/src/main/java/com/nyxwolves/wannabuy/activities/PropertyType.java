package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class PropertyType extends AppCompatActivity implements View.OnClickListener{

    Button continueBtn,backBtn;
    RadioButton selectedProperty;
    TextView minArea,maxArea,modeHeader;

    String property_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_type);

        //propertType = findViewById(R.id.radioGroup);

        modeHeader  = findViewById(R.id.mode_header);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        continueBtn = findViewById(R.id.next_btn);
        continueBtn.setOnClickListener(this);


        selectedProperty = findViewById(R.id.residential);
    }

    public void radioButtonClicked(View v){
        switch (v.getId()){
            case R.id.residential:
                property_type = getString(R.string.residential);
                break;
            case R.id.commercial:
                property_type = getString(R.string.commercial);
                break;
            case R.id.industrial:
                property_type = getString(R.string.industrial);
                break;
            case R.id.instutional:
                property_type = getString(R.string.institutional);
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_btn:
                if(property_type != null) {
                    Intent i = new Intent(PropertyType.this, PropertySubType.class);
                    i.putExtra(getString(R.string.PROPERTY_TYPE), property_type);
                    Requirements.getInstance().type = property_type;
                    startActivity(i);
                }else{
                    Toast.makeText(PropertyType.this,"Select anyone",Toast.LENGTH_SHORT).show();
                }
               break;
            case R.id.back_btn:
                super.onBackPressed();
                break;
        }
    }
}
