package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class Budget extends AppCompatActivity{

    SeekBar minBudgetSeekBar,maxBudgetSeekBar;
    Button nextButton;
    TextView minSelectedPrice,maxSelectedPrice,modeHeader;

    int minBudget = 0;
    int maxBudget = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        modeHeader=findViewById(R.id.budget_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        minSelectedPrice = findViewById(R.id.min_selected_price);
        maxSelectedPrice = findViewById(R.id.max_selected_price);

        minBudgetSeekBar = findViewById(R.id.min_budget_seekbar);
        minBudgetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minBudget = progress;
                if(progress < 100){
                    minSelectedPrice.setText(progress+" Lakhs");
                }else{
                    float inCrores = (float) progress/100;
                    minSelectedPrice.setText(inCrores+"  Crores");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        minBudgetSeekBar.setMax(1000);

        maxBudgetSeekBar = findViewById(R.id.max_budget_seekbar);
        maxBudgetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxBudget = progress;
                if(progress < 100){
                    maxSelectedPrice.setText(progress+" Lakhs");
                }else{
                    float inCrores = (float) progress/100;
                    maxSelectedPrice.setText(inCrores+"  Crores");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        maxBudgetSeekBar.setMax(1000);

        nextButton = findViewById(R.id.budget_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(minBudget != 0 && maxBudget !=0){
                    Requirements.getInstance().minbudget = String.valueOf(minBudget);
                    Requirements.getInstance().maxBudget = String.valueOf(maxBudget);

                    if(Requirements.getInstance().subType.equals(getString(R.string.residential_independent)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.commercial_independent)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.commercial_floorspace)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.industrial_floorspace)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.industrial_independent)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.institutional_independent))){
                        startActivity(new Intent(Budget.this,FurnishedOrNot.class));

                    }else if(Requirements.getInstance().subType.equals(getString(R.string.apartments))){
                        startActivity(new Intent(Budget.this,Bhk.class));

                    }else if(Requirements.getInstance().subType.equals(getString(R.string.residential_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.commercial_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.industrial_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.institutional_land)) ||
                            Requirements.getInstance().subType.equals(getString(R.string.farm_land))){
                        startActivity(new Intent(Budget.this,PropertySize.class));
                    }

                }
            }
        });
    }

}



/*if(checkCondition()){
        Intent i = new Intent(Budget.this, PetsActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(getString(R.string.POST_REQUIREMENT));
        startActivity(i);
        }else{
        Intent i = new Intent(Budget.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(getString(R.string.POST_REQUIREMENT));
        startActivity(i);
        }*/

/*if(Requirements.getInstance().type.equals(getString(R.string.farm_land))) {
                        startActivity(new Intent(Budget.this,RentalEndDate.class));
                    }*/