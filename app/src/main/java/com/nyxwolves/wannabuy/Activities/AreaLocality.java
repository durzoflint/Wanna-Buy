package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class AreaLocality extends AppCompatActivity implements View.OnClickListener{

    Button nextBtn;
    EditText locationInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_locality);

       locationInput =  findViewById(R.id.location_input);
       locationInput.setOnClickListener(this);

       nextBtn= findViewById(R.id.area_next_btn);
       nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location_input:
                break;
            case R.id.area_next_btn:
                break;
        }
    }
}
