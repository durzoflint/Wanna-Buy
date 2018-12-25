package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nyxwolves.wannabuy.R;

public class RentalInvestment extends AppCompatActivity {

    EditText investmentInput;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_investment);

        investmentInput = findViewById(R.id.investment_input);

        nextBtn = findViewById(R.id.rent_invest_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    startActivity(new Intent(RentalInvestment.this,PetsActivity.class));
                }
            }
        });
    }

    private boolean checkInput(){
        if(investmentInput.getText().toString().trim().length() > 0){
            return true;
        }else{
            return false;
        }
    }
}
