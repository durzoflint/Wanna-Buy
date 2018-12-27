package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.nyxwolves.wannabuy.R;

public class FurnishedOrNot extends AppCompatActivity{

   Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furnished_or_not);

        nextBtn = findViewById(R.id.furnished_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FurnishedOrNot.this,PropertySize.class));
            }
        });

    }

    public void radioButtonClicked(View v){

    }
}
