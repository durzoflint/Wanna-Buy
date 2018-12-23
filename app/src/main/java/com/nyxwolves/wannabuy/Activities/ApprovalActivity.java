package com.nyxwolves.wannabuy.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nyxwolves.wannabuy.R;

public class ApprovalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval);
    }

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.dtcp_btn:
                break;
            case R.id.cmda_btn:
                break;
        }
    }
}
