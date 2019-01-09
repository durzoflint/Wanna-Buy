package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class ApprovalActivity extends AppCompatActivity {

    Button nextBtn;
    boolean checkInput = false;
    CheckBox cdma,dtcp,corporation,panchayat,commercial,industrial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                if(Requirements.getInstance().checkApproval()){
                    startActivity(new Intent(ApprovalActivity.this,FacilitiesActivities.class));
                }else{
                    Toast.makeText(ApprovalActivity.this,"Cannot Be Empty",Toast.LENGTH_SHORT).show();
                }
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

    private String setData(CheckBox checkBox){
        if(checkBox.isChecked()){
            Log.d("APPROVAL","YES");
            return getString(R.string.yes);
        }else{
            Log.d("APPROVAL","NO");
            return getString(R.string.no);
        }
    }
    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.dtcp_btn:
                Requirements.getInstance().dtcpApproved = setData((CheckBox)v);
                break;
            case R.id.cmda_btn:
                Requirements.getInstance().cdmaApproved = setData((CheckBox)v);
                break;
            case R.id.panchayat:
                Requirements.getInstance().panchayatApproved = setData((CheckBox)v);
                break;
            case R.id.corp:
                Requirements.getInstance().corporationApproved = setData((CheckBox)v);
                break;
            case R.id.commercial:
                Requirements.getInstance().commercialApproved = setData((CheckBox)v);
                break;
            case  R.id.industrial:
                Requirements.getInstance().industrialApproved = setData((CheckBox)v);
                break;
            case R.id.rera:
                Requirements.getInstance().reraApproved = setData((CheckBox)v);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().cdmaApproved = getString(R.string.not_set_text);
        Requirements.getInstance().dtcpApproved = getString(R.string.not_set_text);
        Requirements.getInstance().corporationApproved = getString(R.string.not_set_text);
        Requirements.getInstance().industrialApproved = getString(R.string.not_set_text);
        Requirements.getInstance().panchayatApproved = getString(R.string.not_set_text);
        Requirements.getInstance().reraApproved = getString(R.string.not_set_text);
    }
}
