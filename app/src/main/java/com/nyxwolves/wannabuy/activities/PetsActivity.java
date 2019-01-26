package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class PetsActivity extends AppCompatActivity {

    Button nextBtn;
    TextView rentalMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rentalMode = findViewById(R.id.rental_mode);
        rentalMode.setText(Requirements.getInstance().buyorRent);

        nextBtn = findViewById(R.id.rent_pets_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PetsActivity.this,ApprovalActivity.class));
            }
        });
    }

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.pets_yes:
                if(((CheckBox)v).isChecked()){
                    Requirements.getInstance().facilitiesList.add(getString(R.string.pets_allowed));
                }else{
                    Requirements.getInstance().facilitiesList.remove(getString(R.string.pets_allowed));
                }
        }
    }

}
