package com.nyxwolves.wannabuy.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nyxwolves.wannabuy.R;

public class WelcomeScreen extends AppCompatActivity {

    Button continueBtn;

    int TIME_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        continueBtn = findViewById(R.id.welcome_continue_btn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeScreen.this,HomeActivity.class));
            }
        });
    }

}
