package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class AgeOfProperty extends AppCompatActivity {

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_of_property);

        nextBtn = findViewById(R.id.age_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Requirements.getInstance().subType.equals(getString(R.string.house)) || Requirements.getInstance().subType.equals(getString(R.string.villa))){
                    startActivity(new Intent(AgeOfProperty.this,CornerActivity.class));
                }else{
                    startActivity(new Intent(AgeOfProperty.this,FurnishedOrNot.class));
                }
            }
        });
    }
}
