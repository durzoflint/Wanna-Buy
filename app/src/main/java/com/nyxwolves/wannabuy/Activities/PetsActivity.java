package com.nyxwolves.wannabuy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nyxwolves.wannabuy.R;

public class PetsActivity extends AppCompatActivity {

    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);

        nextBtn = findViewById(R.id.rent_pets_next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent()
            }
        });
    }

}
