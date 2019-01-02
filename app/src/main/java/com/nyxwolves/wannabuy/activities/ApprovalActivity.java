package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class ApprovalActivity extends AppCompatActivity {

    Button nextBtn;
    boolean checkInput = false;
    RadioButton cdma,dtcp,corporation,panchayat,commercial,industrial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);

        cdma = findViewById(R.id.cmda_btn);
        dtcp = findViewById(R.id.dtcp_btn);
        corporation = findViewById(R.id.corp);
        panchayat=  findViewById(R.id.panchayat);
        commercial  = findViewById(R.id.commercial);
        commercial.setVisibility(View.GONE);
        industrial = findViewById(R.id.industrial);
        industrial.setVisibility(View.GONE);

        showOptions();
        nextBtn = findViewById(R.id.approval_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(checkInput){
                    startActivity(new Intent(ApprovalActivity.this,FacilitiesActivities.class));
                //}else{
                  //  Toast.makeText(ApprovalActivity.this,"Cannot Be Empty",Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }


    private void showOptions(){
        if(Requirements.getInstance().type.equals(getString(R.string.commercial))){
            commercial.setVisibility(View.VISIBLE);
        }else if(Requirements.getInstance().type.equals(getString(R.string.industrial))){
            industrial.setVisibility(View.VISIBLE);
        }
    }
    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.dtcp_btn:
                checkInput = true;
                Requirements.getInstance().approval = getString(R.string.dtcp_text);
                break;
            case R.id.cmda_btn:
                checkInput = true;
                Requirements.getInstance().approval = getString(R.string.cdma_text);
                break;
            case R.id.commercial:
                checkInput = true;
                Requirements.getInstance().approval = getString(R.string.commercial);
                break;
            case  R.id.industrial:
                checkInput = true;
                Requirements.getInstance().approval = getString(R.string.industrial);
                break;
        }
    }
}
