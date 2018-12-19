package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class AreaLocality extends AppCompatActivity implements View.OnClickListener{

    Button continueBtn,backBtn;
    TextInputEditText areaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_locality);

        areaText = findViewById(R.id.area_input);

        continueBtn = findViewById(R.id.continue_btn);
        continueBtn.setOnClickListener(this);

        backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_btn:
                if(areaText.getText().toString().length() >  0){
                    Intent i = new Intent(AreaLocality.this,PropertyType.class);
                    Requirements.getInstance().area = areaText.getText().toString();
                    startActivity(i);
                }else{
                    Toast.makeText(AreaLocality.this, "Please Enter a Location", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back_btn:
                super.onBackPressed();
                break;
        }
    }
}
