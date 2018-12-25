package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class NewOrResale extends AppCompatActivity {

    Button nextButton;
    TextView modeHeader;

    String newOrResale =null;//new =1 resale = 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_or_resale);

        modeHeader =  findViewById(R.id.new_resale_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        nextButton = findViewById(R.id.new_resale_next_btn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newOrResale != null){
                    Requirements.getInstance().isNew = newOrResale;
                    startActivity(new Intent(NewOrResale.this,Budget.class));
                }else{
                    Toast.makeText(NewOrResale.this,"Should select one",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.new_btn:
                newOrResale = "NEW_PROPERTY";
                break;
            case R.id.resale_btn:
                newOrResale = "RESALE_PROPERTY";
                break;
        }
    }

}
