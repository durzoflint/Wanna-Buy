package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        modeHeader  = findViewById(R.id.facing_mode);
        modeHeader.setText(Requirements.getInstance().buyorRent);

        nextBtn = findViewById(R.id.facing_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (direction != null){
                    Requirements.getInstance().facing = direction;
                    startActivity(new Intent(FacingActivity.this,ApprovalActivity.class));
                }else{
                    Toast.makeText(FacingActivity.this,"Choose any direction",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onCheckBoxClicked(View v){
        switch (v.getId()){
            case R.id.north_check_box:
                direction = getString(R.string.north_text);
                break;
            case R.id.south_check_box:
                direction = getString(R.string.south_text);
                break;
            case R.id.west_check_box:
                direction = getString(R.string.west_text);
                break;
            case R.id.east_check_box:
                direction = getString(R.string.east_text);
                break;
        }
    }
}
