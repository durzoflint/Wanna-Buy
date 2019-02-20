package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class BrokerageActivity extends AppCompatActivity {

    LinearLayout brokerageLayout;
    Button nextBtn;
    EditText brokerageInput;

    boolean isBrokerageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brokerage);

        nextBtn = findViewById(R.id.brokerage_next_btn);
        brokerageLayout = findViewById(R.id.brokerage_input_layout);
        brokerageInput = findViewById(R.id.req_brokerage_per_cent_input);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBrokerage()){
                    startActivity(new Intent(BrokerageActivity.this,RequirementName.class));
                }else{
                    Toast.makeText(BrokerageActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkBrokerage(){
        if(isBrokerageSelected){
            if(brokerageInput.getText().toString().length() > 0){
                Requirements.getInstance().brokeragePerCent = brokerageInput.getText().toString().trim();
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }
    }

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.req_brokerage_yes:
                isBrokerageSelected = true;
                brokerageLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.req_brokerage_no:
                isBrokerageSelected = false;
                brokerageLayout.setVisibility(View.GONE);
                break;
        }
    }
}
