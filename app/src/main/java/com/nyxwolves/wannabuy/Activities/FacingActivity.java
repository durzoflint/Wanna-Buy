package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nyxwolves.wannabuy.POJO.Requirements;
import com.nyxwolves.wannabuy.R;

public class FacingActivity extends AppCompatActivity {

    Button nextBtn;

    String direction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facing);

        nextBtn = findViewById(R.id.facing_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (direction != null){
                    Requirements.getInstance().facing = direction;
                    startActivity(new Intent(FacingActivity.this,NewOrResale.class));
                }else{
                    Toast.makeText(FacingActivity.this,"Choose any direction",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRadioButtonClicked(View v){
        switch (v.getId()){
            case R.id.north:
                direction = getString(R.string.north_text);
                break;
            case R.id.south:
                direction = getString(R.string.south_text);
                break;
            case R.id.west:
                direction = getString(R.string.west_text);
                break;
            case R.id.east:
                direction = getString(R.string.east_text);
                break;
        }
    }
}
