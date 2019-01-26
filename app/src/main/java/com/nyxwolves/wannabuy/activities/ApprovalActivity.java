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

    private void setData(CheckBox checkBox,String data){
        if(checkBox.isChecked()){
            Requirements.getInstance().approvalList.add(data);

        }else{
            Requirements.getInstance().approvalList.remove(data);
        }
    }
    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.dtcp_btn:
                setData((CheckBox)v,getString(R.string.dtcp_text));
                break;
            case R.id.cmda_btn:
                setData((CheckBox)v,getString(R.string.cdma_text));
                break;
            case R.id.panchayat:
                setData((CheckBox)v,getString(R.string.panchayat));
                break;
            case R.id.corp:
                setData((CheckBox)v,getString(R.string.corporation));
                break;
            case R.id.commercial:
                setData((CheckBox)v,getString(R.string.commercial));
                break;
            case  R.id.industrial:
                setData((CheckBox)v,getString(R.string.industrial));
                break;
            case R.id.rera:
                setData((CheckBox)v,getString(R.string.rera_text));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Requirements.getInstance().approvalList.clear();
    }
}
