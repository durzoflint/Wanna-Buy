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

public class NewOrResale extends AppCompatActivity {

    Button nextButton;
    TextView modeHeader;

    String newOrResale = null;//new =1 resale = 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_or_resale);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        modeHeader = findViewById(R.id.new_resale_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        nextButton = findViewById(R.id.new_resale_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Requirements.getInstance().checkNewOrResale()) {
                    startActivity(new Intent(NewOrResale.this, Building.class));
                }

            }
        });
    }

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.new_check:
                Requirements.getInstance().isNew = getString(R.string.new_text);
                break;
            case R.id.resale_check:
                Requirements.getInstance().isResale = getString(R.string.resale_text);
                break;
        }
    }


}
