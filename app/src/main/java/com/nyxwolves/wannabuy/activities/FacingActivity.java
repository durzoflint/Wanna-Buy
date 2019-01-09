package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class FacingActivity extends AppCompatActivity {

    Button nextBtn;
    TextView modeHeader;
    String direction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facing);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        modeHeader  = findViewById(R.id.facing_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        nextBtn = findViewById(R.id.facing_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Requirements.getInstance().checkFacing()){
                    if(Requirements.getInstance().subType.equals(getString(R.string.residential_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.commercial_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.industrial_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.institutional_land))){
                        startActivity(new Intent(FacingActivity.this,ApprovalActivity.class));
                    }else {
                        startActivity(new Intent(FacingActivity.this, AgeOfProperty.class));
                    }
                }else{
                    Toast.makeText(FacingActivity.this,"Choose any direction",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.north_check_box:
                Requirements.getInstance().facingNorth = getString(R.string.yes);
                Requirements.getInstance().facingSouth = getString(R.string.no);
                Requirements.getInstance().facingEast = getString(R.string.no);
                Requirements.getInstance().facingWest = getString(R.string.no);
                break;
            case R.id.south_check_box:
                Requirements.getInstance().facingNorth = getString(R.string.no);
                Requirements.getInstance().facingSouth = getString(R.string.yes);
                Requirements.getInstance().facingEast = getString(R.string.no);
                Requirements.getInstance().facingWest = getString(R.string.no);
                break;
            case R.id.west_check_box:
                Requirements.getInstance().facingNorth = getString(R.string.no);
                Requirements.getInstance().facingSouth = getString(R.string.no);
                Requirements.getInstance().facingEast = getString(R.string.no);
                Requirements.getInstance().facingWest = getString(R.string.yes);
                break;
            case R.id.east_check_box:
                Requirements.getInstance().facingNorth = getString(R.string.no);
                Requirements.getInstance().facingSouth = getString(R.string.no);
                Requirements.getInstance().facingEast = getString(R.string.yes);
                Requirements.getInstance().facingWest = getString(R.string.no);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().facingWest = getString(R.string.not_set_text);
        Requirements.getInstance().facingNorth = getString(R.string.not_set_text);
        Requirements.getInstance().facingSouth = getString(R.string.not_set_text);
        Requirements.getInstance().facingEast = getString(R.string.not_set_text);
    }
}
